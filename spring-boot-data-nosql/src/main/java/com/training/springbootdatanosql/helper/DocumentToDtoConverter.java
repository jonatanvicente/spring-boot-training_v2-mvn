package com.training.springbootdatanosql.helper;

import com.training.springbootdatanosql.document.LanguageDocument;
import com.training.springbootdatanosql.dto.LanguageDto;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.time.format.DateTimeFormatter;

@Component
public class DocumentToDtoConverter<S,D> {

/*
    public Flux<D> convertDocumentFluxToDtoFlux(Flux<S> documentFlux, Class<D> dtoClass) {
        return documentFlux.map(doc -> convertDocumentToDto(doc, dtoClass));
    }

    static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
*/

/*    public D convertDocumentToDto(S document, Class<D> dtoClass){
        ModelMapper mapper = new ModelMapper();

       // if(dtoClass.isAssignableFrom(LanguageDto.class)) {
            mapper.createTypeMap(LanguageDocument.class, LanguageDto.class)
                    .addMapping(LanguageDocument::getIdLanguage,LanguageDto::setLanguageId);
        //}

        return mapper.map(document, dtoClass);
    }*/

}
