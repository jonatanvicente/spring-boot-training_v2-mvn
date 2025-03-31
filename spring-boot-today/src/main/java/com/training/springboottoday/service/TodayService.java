package com.training.springboottoday.service;

import com.training.springboottoday.dto.TodayJsonDto;
import com.training.springboottoday.dto.TodayPattern;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TodayService {

    private final String DEFAULT_PATTERN = "dd/MM/yy HH:mm:ss";

    public String getTodaySimple(){
        return getToday(DEFAULT_PATTERN);
    }

    public Mono<TodayJsonDto> getTodayObject(){
        TodayJsonDto response = new TodayJsonDto(getToday(DEFAULT_PATTERN));
        return Mono.just(response);
    }

    public Mono<TodayJsonDto> getTodayParameterized(TodayPattern pattern){
        TodayJsonDto response = new TodayJsonDto(getToday(pattern.getPattern()));
        return Mono.just(response);
    }

    private String getToday(String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
