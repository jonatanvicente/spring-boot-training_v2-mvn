package com.training.springbootdatanosql.controller;

import com.training.springbootdatanosql.dto.GenericResultDto;
import com.training.springbootdatanosql.dto.LanguageDto;
import com.training.springbootdatanosql.service.IDataNoSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping(value = "/springboottraining/api/v1/datanosql")
public class DataNoSqlController {


    private static final Logger log = LoggerFactory.getLogger(DataNoSqlController.class);
    @Autowired
    IDataNoSqlService service;

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing(){
        return "Bonjour !!!";
    }


    @GetMapping("/language")
    public Mono<GenericResultDto<LanguageDto>> getAllLanguages() {
        return service.getAllLanguages();
    }
}