package com.example.somkiat;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("/call")
    public String call() {
        log.info("Start call");
        try {
            String result =
                    restTemplate.getForObject(
                            "http://10.10.99.128:8080/call", String.class);
            log.info("Finish call");
            meterRegistry.counter("call_xxx",
                    "status", "success",
                    "exception", "N/A").increment();
            return result;
        } catch(Exception e) {
            meterRegistry.counter("call_xxx",
                    "status", "fail"
            , "exception", e.getMessage()).increment();
        }
        return "N/A";
    }

}
