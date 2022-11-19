package com.sa.web;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentimentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import java.net.UnknownHostException;

@CrossOrigin(origins = "*")
@RestController
public class SentimentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SentimentController.class);

    @Value("${sa.logic.api.url}")
    private String saLogicApiUrl;

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto) {
        RestTemplate restTemplate = new RestTemplate();
        LOGGER.info("Incoming message ='{}'", sentenceDto);
        SentimentDto response = restTemplate.postForEntity(saLogicApiUrl + "/analyse/sentiment",
                sentenceDto, SentimentDto.class).getBody();
        LOGGER.info("Incoming message {} has RESPONSE = {}", sentenceDto, response);
        return response;
    }

    @GetMapping("/testHealth")
    public String testHealth() {
        return "all ok";
    }

    @ExceptionHandler(value
            = {UnknownHostException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        LOGGER.error("Incoming request '{}' has error {}", request, ex.getMessage());

        return new ResponseEntity(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }
}


