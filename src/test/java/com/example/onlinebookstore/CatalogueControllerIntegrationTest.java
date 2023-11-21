package com.example.onlinebookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogueControllerIntegrationTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testShowPaymentPage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/checkout",
                String.class)).contains("Checkout");
    }

    @Test
    public void testCreateCheckoutSession() throws Exception {
        wireMockServer.stubFor(post(urlEqualTo("/v1/prices"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"id\":\"price_123\",\"unit_amount\":499,\"currency\":\"usd\"}")));


        Map<String, Object> payload = new HashMap<>();
        payload.put("totalAmount", 499);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/create-checkout-session",
                payload,
                String.class
        );

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        Map<String, String> response = objectMapper.readValue(responseEntity.getBody(), Map.class);
        assertThat(response).containsKey("id");
        assertThat(response.get("id")).startsWith("cs_");
    }
}
