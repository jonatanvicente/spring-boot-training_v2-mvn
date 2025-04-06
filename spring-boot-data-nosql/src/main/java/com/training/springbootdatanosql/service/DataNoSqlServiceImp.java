package com.training.springbootdatanosql.service;

import com.training.springbootdatanosql.document.LanguageDocument;
import com.training.springbootdatanosql.dto.GenericResultDto;
import com.training.springbootdatanosql.dto.LanguageDto;
import com.training.springbootdatanosql.helper.DocumentToDtoConverter;
import com.training.springbootdatanosql.repository.LanguageRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DataNoSqlServiceImp implements IDataNoSqlService {

    private static final Logger log = LoggerFactory.getLogger(DataNoSqlServiceImp.class);

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private DocumentToDtoConverter<LanguageDocument, LanguageDto> languageConverter = new DocumentToDtoConverter<>();

    @Cacheable (value = "allLanguages")
    public Mono<GenericResultDto<LanguageDto>> getAllLanguages() {
        Flux<LanguageDto> languagesDto = languageConverter.convertDocumentFluxToDtoFlux(languageRepository.findAll(), LanguageDto.class);
        return languagesDto.collectList().map(language -> {
            GenericResultDto<LanguageDto> resultDto = new GenericResultDto<>();
            resultDto.setInfo(0, language.size(), language.size(), language.toArray(new LanguageDto[0]));
            return resultDto;
        });
    }

    }
