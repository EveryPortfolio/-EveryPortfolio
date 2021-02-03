package com.everyportfolio.portfolio.controller;

import com.everyportfolio.portfolio.DTO.*;
import com.everyportfolio.portfolio.service.LikeListService;
import com.everyportfolio.portfolio.service.PortfolioService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class PortfolioController {
    private final Logger log = LoggerFactory.getLogger(PortfolioController.class);
    private Gson gson;
    private PortfolioService portfolioService;
    private LikeListService likeListService;

    @PostMapping("/create")
    public ResponseEntity<HashMap<String, Object>> createPortfolio(@RequestBody PortfolioDTO portfolio, @RequestHeader("access-token") String accessToken) {
        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();

        portfolioService.createPortfolio(id, portfolio.getTitle(), portfolio.getContent(), portfolio.getTemplateType());

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update/title")
    public ResponseEntity<HashMap<String, Object>> updatePortfolioTitle(@RequestBody PortfolioTitleDTO title, @RequestHeader("access-token") String accessToken) throws Exception{
        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();
        if(!portfolioService.compareUserIdWithCreator(id, title.getTableId()))
            throw new Exception();

        portfolioService.updatePortfolioTitle(title.getTableId(), title.getTitle());

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "OK");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update/content")
    public ResponseEntity<HashMap<String, Object>> updatePortfolioContent(@RequestBody PortfolioContentDTO content, @RequestHeader("access-token") String accessToken) throws Exception{
        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();
        if(!portfolioService.compareUserIdWithCreator(id, content.getTableId()))
            throw new Exception();

        portfolioService.updatePortfolioContent(content.getTableId(), content.getTemplateType(), content.getContent());
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "OK");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> deletePortfolio(@RequestBody PortfolioDeleteDTO delete, @RequestHeader("access-token") String accessToken) throws Exception {
        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();

        if(!portfolioService.compareUserIdWithCreator(id, delete.getTableId()))
            throw new Exception();

        portfolioService.deletePortfolio(delete.getTableId());

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "OK");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<HashMap<String, Object>> viewPortfolio(@RequestParam("tableId") int tableId) {

        String content = gson.toJson(portfolioService.selectPortfolioByTableId(tableId));
        HashMap<String, Object> result = gson.fromJson(content, new TypeToken<HashMap<String, Object>>(){}.getType());

        result.put("status", 200);
        result.put("message", "OK");
        result.put("tableId", (int)Double.parseDouble(result.get("tableId").toString()));
        result.put("likeCount", likeListService.selectLikeCountByTableId(tableId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
