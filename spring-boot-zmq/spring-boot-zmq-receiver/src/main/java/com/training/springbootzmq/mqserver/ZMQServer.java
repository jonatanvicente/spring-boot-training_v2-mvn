package com.training.springbootzmq.mqserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.springbootzmq.dto.RequestDto;
import com.training.springbootzmq.dto.ResponseDto;
import com.training.springbootzmq.helper.ObjectSerializer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.Optional;

@Component
//WARNING: use this annotation only to get many instances of this class, disables Spring singleton default behavior
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ZMQServer {

    private final ZContext context;
    private final String SOCKET_ADDRESS;
    private static final Logger log = LoggerFactory.getLogger(ZMQServer.class);

    @Autowired
    ObjectSerializer objectSerializer;

    public ZMQServer(ZContext context, @Value("${zeromq.socket.address}") String socketAddress){
        this.context = context;
        this.SOCKET_ADDRESS = socketAddress;
    }

    @PostConstruct
    public void init() {
        log.info("Starting ZMQ Server");
        new Thread(this::run).start();
    }

    public void run(){
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                byte[] reply = socket.recv(0);

                Optional<Object> request = Optional.empty();
                try {
                    request = Optional.of(objectSerializer.deserialize(reply, RequestDto.class));
                } catch (IOException e) {
                    log.error(e.getMessage());
                }

                log.info("Received from ZMQClient: [" + ((RequestDto)request.get()).getUuid() + "]");
                log.info("Sending to ZMQClient: [99]");
                ResponseDto dto = new ResponseDto();
                dto.setPercent(99);

                Optional<byte[]> response = Optional.empty();
                try {
                    response = Optional.of(objectSerializer.serialize(dto));
                } catch (JsonProcessingException e) {
                    log.error(e.getMessage());
                }

                socket.send(response.orElse(new byte[0]), 0);
            }
        }
    }
}

