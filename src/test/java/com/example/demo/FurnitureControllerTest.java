package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.demo.dto.FurnitureDTO;
import com.example.demo.dto.FurnitureType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FurnitureControllerTest {

    TestRestTemplate restTemplate;
    URL base;
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate();
        base = new URL("http://localhost:" + port);
    }

    @Test
    public void whenDescriptionIsNullThenBAD_REQUEST()
            throws IllegalStateException, IOException {
        FurnitureDTO furnitureDTO = FurnitureDTO.builder().width(3.6).length(9.2)
                .description(null)
                .furnitureType(FurnitureType.BED).build();
        ResponseEntity<String> response = restTemplate.postForEntity(
                base.toString() + "/api/furniture/create-new-furniture",
                furnitureDTO,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void whenRequestBodyIsValidShouldBeCREATED() {
        BigDecimal price = new BigDecimal("34.12");
        FurnitureDTO furnitureDTO = FurnitureDTO.builder().price(price).width(3.6).length(9.2)
                .description("A good confortable bed")
                .furnitureType(FurnitureType.BED).build();
        ResponseEntity<String> response = restTemplate.postForEntity(
                base.toString() + "/api/furniture/create-new-furniture",
                furnitureDTO,
                String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

}
