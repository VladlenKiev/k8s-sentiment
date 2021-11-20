package com.sa.web;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentimentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
public class SentimentController {

    @Value("${sa.logic.api.url}")
    private String saLogicApiUrl;

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto) {
        RestTemplate restTemplate = new RestTemplate();
//        SentimentDto sentimentDto = new SentimentDto();
//        sentimentDto.setSentence(sentenceDto.getSentence());
//        sentimentDto.setPolarity(125f);
//        System.out.println("sentimentDto = " + sentimentDto);
//        return sentimentDto;
        System.out.println("Incoming message = " + sentenceDto);
        return restTemplate.postForEntity(saLogicApiUrl + "/analyse/sentiment",
                sentenceDto, SentimentDto.class)
                .getBody();
    }

    @GetMapping("/testHealth")
    public String testHealth() {
        return "all ok";
    }
}


