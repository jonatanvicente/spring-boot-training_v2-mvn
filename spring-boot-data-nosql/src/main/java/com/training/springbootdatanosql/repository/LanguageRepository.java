package com.training.springbootdatanosql.repository;

import com.training.springbootdatanosql.document.LanguageDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface LanguageRepository extends ReactiveMongoRepository<LanguageDocument, UUID> {

    Mono<LanguageDocument> findByIdLanguage(UUID id);
    Mono<Void> deleteByIdLanguage(UUID id);
}
