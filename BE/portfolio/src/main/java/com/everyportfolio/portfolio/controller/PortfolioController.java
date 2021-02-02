package com.everyportfolio.portfolio.controller;

import com.everyportfolio.portfolio.DTO.AccessTokenDTO;
import com.everyportfolio.portfolio.DTO.PortfolioDTO;
import com.everyportfolio.portfolio.service.PortfolioService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AllArgsConstructor
public class PortfolioController {
    private final Logger log = LoggerFactory.getLogger(PortfolioController.class);
    private Gson gson;
    private PortfolioService portfolioService;

    @PostMapping("/create")
    public ResponseEntity<HashMap<String, Object>> createPortfolio(@RequestBody PortfolioDTO portfolio, @RequestHeader("access-token") String accessToken) {
        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();
        HashMap<String, Object> result = new HashMap<>();
        portfolioService.createProtfolio(id, portfolio.getTitle(), portfolio.getContent(), portfolio.getTemplateType());
        result.put("status", 200);
        result.put("message", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
