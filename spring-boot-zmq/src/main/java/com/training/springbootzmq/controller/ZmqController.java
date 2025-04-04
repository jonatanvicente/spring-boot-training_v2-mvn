package com.training.springbootzmq.controller;

import com.training.springbootzmq.config.PropertiesConfig;
import com.training.springbootzmq.mqclient.ZMQClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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


    @GetMapping(value = "/test")
    public String test() {
        log.info("** Saludos desde el logger **");


        zmqClient.sendMessage(challengeInputDto, StatisticsResponseDto.class)
                .thenAccept(response ->
                        log.info("[ Response: {}" , ((StatisticsResponseDto) response).getPercent() + " ]"))
                .exceptionally(e -> {
                    log.error(e.getMessage());
                    return null;
                });

        return "Starting ZMQ Comms!!!";
    }
}
