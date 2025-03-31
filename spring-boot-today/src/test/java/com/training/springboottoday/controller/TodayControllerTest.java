package com.training.springboottoday.controller;

import com.training.springboottoday.dto.TodayJsonDto;
import com.training.springboottoday.dto.TodayPattern;
import com.training.springboottoday.service.TodayService;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TodayController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodayControllerTest {

    private final String CONTROLLER_BASE_URL = "/springboottraining/api/v1/today";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TodayService todayService;

    @Test
    @DisplayName("Test response Hello")
    @Order(1)
    public void pingTest(){
        final String URI_TEST = "/ping";

        webTestClient.get()
                .uri(CONTROLLER_BASE_URL + URI_TEST)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(s -> s.toString(), equalTo("Bonjour !!!"));
    }

    @Test
    @DisplayName("Test response JSON")
    @Order(2)
    public void todayJsonTest(){
        final String URI_TEST = "/todayJson";

        TodayJsonDto mockDto = new TodayJsonDto("01/01/70 00:00:00");

        //condicionado de respuesta
        when(todayService.getTodayObject()).thenReturn(Mono.just(mockDto));

        webTestClient.get()
                .uri(CONTROLLER_BASE_URL + URI_TEST)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.today")
                .isEqualTo("01/01/70 00:00:00");
    }


    @Test
    @DisplayName("Test request GET with body JSON")
    @Order(3)
    public void todayJsonParameterizedTest(){

        final String URI_TEST = "/todayJsonParameterized";

        TodayPattern patternMock = new TodayPattern();
        patternMock.setPattern("dd/MM/yy HH:mm:ss");
        TodayJsonDto mockDto = new TodayJsonDto("01/01/70 00:00:00");

        when(todayService.getTodayObject()).thenReturn(Mono.just(mockDto));
//TODO- error test
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pattern", "dd/MM/yy HH:mm:ss");

        webTestClient.method(HttpMethod.GET)
                .uri(CONTROLLER_BASE_URL + URI_TEST)
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsonObject))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.today")
                .isEqualTo("01/01/70 00:00:00");
    }
}
