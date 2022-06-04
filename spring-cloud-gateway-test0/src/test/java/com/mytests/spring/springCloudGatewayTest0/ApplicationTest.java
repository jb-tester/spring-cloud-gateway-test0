package com.mytests.spring.springCloudGatewayTest0;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;
import wiremock.com.fasterxml.jackson.databind.JsonNode;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {"my.server.url=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
public class ApplicationTest {

  @Autowired
  private WebTestClient webClient;

  @Test
  public void contextLoads() throws Exception {

    JsonNode jsonNode = new ObjectMapper().readTree("{" +
            "    \"userId\": 1003," +
            "    \"id\": 1003," +
            "    \"title\": \"Some title\"," +
            "    \"body\": \"Some body\"" +
            "}");
    //Stubs
    stubFor(get(urlEqualTo("/test1"))
        .willReturn(aResponse()
                        .withBody("foo")
          //.withJsonBody(jsonNode)
         // .withHeader("Content-Type", "application/json")
                ));
    stubFor(get(urlEqualTo("/test3"))
      .willReturn(aResponse()
        .withBody("no fallback")
        .withFixedDelay(3000)));

    webClient
      .get().uri("/test1")
      .exchange()
      .expectStatus().isOk()
      .expectBody(String.class).isEqualTo("foo");


    webClient
      .get().uri("/test3")
      .header("Host", "foo.com")
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .consumeWith(
        response -> assertThat(response.getResponseBody()).isEqualTo("alas it's only me".getBytes()));
  }
}