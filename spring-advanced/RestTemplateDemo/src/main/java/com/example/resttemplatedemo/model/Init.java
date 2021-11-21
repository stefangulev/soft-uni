package com.example.resttemplatedemo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class Init implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(Init.class);

    public Init(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<BookDto[]> booksResponse = restTemplate.getForEntity("http://localhost:8080/books", BookDto[].class);

        if(booksResponse.hasBody()) {
            BookDto[] body = booksResponse.getBody();
            for (BookDto bookDto : body) {
                LOGGER.info("A book came from the server: {}", bookDto);
            }
        }
    }
}
