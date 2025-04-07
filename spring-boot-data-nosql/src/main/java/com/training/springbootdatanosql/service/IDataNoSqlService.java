package com.training.springbootdatanosql.service;

import com.training.springbootdatanosql.dto.GenericResultDto;
import com.training.springbootdatanosql.dto.LanguageDto;
import reactor.core.publisher.Mono;

public interface IDataNoSqlService {

    Mono<GenericResultDto<LanguageDto>> getAllLanguages();
    //void getAllLanguages();

}
