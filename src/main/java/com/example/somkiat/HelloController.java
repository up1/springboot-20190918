package com.example.somkiat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);


    @GetMapping("/call")
    public String call() {
        log.info("Start call");
        String result =
                restTemplate.getForObject(
                        "http://10.10.99.128:8080/call", String.class);
        log.info("Finish call");
        return result;
    }

}
