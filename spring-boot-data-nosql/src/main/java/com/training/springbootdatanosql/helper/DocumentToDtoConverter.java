package com.training.springbootdatanosql.helper;

import com.training.springbootdatanosql.document.LanguageDocument;
import com.training.springbootdatanosql.dto.LanguageDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DocumentToDtoConverter<S,D> {

    public Flux<D> convertDocumentFluxToDtoFlux(Flux<S> documentFlux, Class<D> dtoClass) {
        return documentFlux.map(doc -> convertDocumentToDto(doc, dtoClass));
    }

    public D convertDocumentToDto(S document, Class<D> dtoClass){
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(LanguageDocument.class, LanguageDto.class)
                .addMapping(LanguageDocument::getIdLanguage,LanguageDto::setLanguageId);

        return mapper.map(document, dtoClass);
    }

}
