package com.training.springboottoday.controller;

import com.training.springboottoday.dto.TodayPattern;
import com.training.springboottoday.service.TodayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/springboottraining/api/v1/today")
public class TodayController {

    @Autowired
    private TodayService todayService;

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing(){
        return "Bonjour !!!";
    }


    @Operation(summary = "You can call this to get date", tags = "Get Date")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
    })
    @GetMapping("/today")
    @ResponseStatus(HttpStatus.OK)
    public String getToday(){
        return todayService.getTodaySimple();
    }

    @Operation(summary = "You can call this to get today's date in JSON format", tags = "Get Today's Date")
    @GetMapping("/todayJson")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getTodayJson(){

        return todayService.getTodayObject();
    }

    @Operation(summary = "You can call this to get today's date in JSON format, but delayed...", tags = "Get Today's Date")
    @GetMapping("/todayJsonDelayed")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getTodayJsonDelayed() {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
        return todayService.getTodayObject();
    }

    /**
     * Input Format example:
     *      {
     *     "pattern": "dd/MM/yyyy HH:mm:ss"
     *      }
     * @param pattern
     * @return
     */
    @Operation(summary = "You can call this to get today's date in JSON format", tags = "Get Today's Date")
    @GetMapping("/todayJsonParameterized")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getTodayJsonWithParam(@RequestBody TodayPattern pattern){
        return todayService.getTodayParameterized(pattern);
    }


}
