package com.everyportfolio.api_gateway.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public ResponseEntity<Object> hello() {
        return restTemplate.getForEntity("http://user:8080/hello", Object.class);
    }
}
