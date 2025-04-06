package com.training.springbootdatanosql.controller;

import com.training.springbootdatanosql.dto.GenericResultDto;
import com.training.springbootdatanosql.dto.LanguageDto;
import com.training.springbootdatanosql.service.IDataNoSqlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/language")
    @Operation(
            operationId = "Get all the stored languages into the Database.",
            summary = "Get to see all id language and name.",
            description = "Requesting all the languages through the URI from the database.",
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = GenericResultDto.class), mediaType = "application/json")}),
            }
    )
    public Mono<GenericResultDto<LanguageDto>> getAllLanguages() {
        return service.getAllLanguages();
    }
}