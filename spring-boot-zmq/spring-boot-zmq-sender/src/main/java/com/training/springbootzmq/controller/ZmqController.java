package com.training.springbootzmq.controller;

import com.training.springbootzmq.config.PropertiesConfig;
import com.training.springbootzmq.dto.RequestDto;
import com.training.springbootzmq.dto.ResponseDto;
import com.training.springbootzmq.mqclient.ZMQClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Validated
@RequestMapping(value = "/springboottraining/api/v1/zmq")
public class ZmqController {

    private static final Logger log = LoggerFactory.getLogger(ZmqController.class);

    public ZmqController(PropertiesConfig config) {
        this.config = config;
    }

    @Autowired
    private final PropertiesConfig config;

    @Autowired
    ZMQClient zmqClient;

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    RequestDto requestDto;


    @GetMapping(value = "/internalcomm")
    public String test() {
        requestDto.setUuid(UUID.fromString("dcacb291-b4aa-4029-8e9b-284c8ca80296"));
        log.info("Sending to ZMQServer: [" + requestDto.getUuid() + "]");

        zmqClient.sendMessage(requestDto, ResponseDto.class)
                .thenAccept(response ->
                        log.info("[ Response received from ZMQServer: {}" , ((ResponseDto) response).getPercent() + " ]"))
                .exceptionally(e -> {
                    log.error(e.getMessage());
                    return null;
                });

        return "Starting ZMQ Comms!!!";
    }
}
