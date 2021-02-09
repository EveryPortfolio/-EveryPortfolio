package com.everyportfolio.portfolio.controller;

import com.everyportfolio.portfolio.DTO.*;
import com.everyportfolio.portfolio.exception.*;
import com.everyportfolio.portfolio.model.Portfolio;
import com.everyportfolio.portfolio.service.LikeListService;
import com.everyportfolio.portfolio.service.PortfolioService;
import com.everyportfolio.portfolio.utility.RegularExpressionUtility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
public class PortfolioController {
    private final Logger log = LoggerFactory.getLogger(PortfolioController.class);
    private Gson gson;
    private PortfolioService portfolioService;
    private LikeListService likeListService;
    private RegularExpressionUtility regularExpressionUtility;

    @PostMapping("/create")
    public ResponseEntity<HashMap<String, Object>> createPortfolio(@RequestBody PortfolioDTO portfolio, @RequestHeader("access-token") String accessToken) throws TooLongTitleException, TooLongThumbnailURLException{
        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();

        if(portfolio.getTitle() != null && portfolio.getTitle().length() > 50)
            throw new TooLongTitleException(id + "'s portfolio title request is too long");

        if(portfolio.getThumbnailURL() != null && portfolio.getThumbnailURL().length() > 2048)
            throw new TooLongThumbnailURLException(id + "'s portfolio thumbnail URL is too long");

        portfolioService.createPortfolio(id, portfolio.getTitle(), portfolio.getContent(), portfolio.getTemplateType(), portfolio.getThumbnailURL());

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update/title")
    public ResponseEntity<HashMap<String, Object>> updatePortfolioTitle(@RequestBody PortfolioTitleDTO title, @RequestHeader("access-token") String accessToken) throws Exception{
        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();

        if(title.getTitle() != null && title.getTitle().length() > 50)
            throw new TooLongTitleException(id + "'s portfolio title request is too long");

        if(!portfolioService.compareUserIdWithCreator(id, title.getTableId()))
            throw new NotEqualToWriterException(id + "'s request is not equal to " + title.getTableId() + "'s writer");

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
            throw new NotEqualToWriterException(id + "'s request is not equal to " + content.getTableId() + "'s writer");

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
            throw new NotEqualToWriterException(id + "'s request is not equal to " + delete.getTableId() + "'s writer");

        portfolioService.deletePortfolio(delete.getTableId());

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "OK");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<HashMap<String, Object>> viewPortfolio(@RequestParam("tableId") int tableId) throws Exception{
        if(!portfolioService.existPortfolioByTableId(tableId))
            throw new NotExistPortfolioException(tableId + " doesn't exist");

        String content = gson.toJson(portfolioService.selectPortfolioByTableId(tableId));
        HashMap<String, Object> result = gson.fromJson(content, new TypeToken<HashMap<String, Object>>(){}.getType());

        result.put("status", 200);
        result.put("message", "OK");
        result.put("tableId", (int)Double.parseDouble(result.get("tableId").toString()));
        result.put("likeCount", likeListService.selectLikeCountByTableId(tableId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<HashMap<String, Object>> switchLikeList(@RequestBody PortfolioLikeDTO like, @RequestHeader("access-token") String accessToken) throws Exception{

        if(!portfolioService.existPortfolioByTableId(like.getTableId()))
            throw new NotExistPortfolioException(like.getTableId() + " doesn't exist");

        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();

        likeListService.switchLikeByTableId(like.getTableId(), id);
        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("message", "OK");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/like")
    public ResponseEntity<HashMap<String, Object>> getLikeList(@RequestParam("tableId") int tableId, @RequestHeader("access-token") String accessToken) throws Exception{
        if(!portfolioService.existPortfolioByTableId(tableId))
            throw new NotExistPortfolioException(tableId + " doesn't exist");

        String id = gson.fromJson(accessToken, AccessTokenDTO.class).getId();

        HashMap<String, Object> result = new HashMap<>();

        result.put("status", 200);
        result.put("message", "OK");
        if(likeListService.checkLikeByTableIdAndUserId(tableId, id))
            result.put("like", true);
        else
            result.put("like", false);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<HashMap<String, Object>> searchPortfolioList(@RequestParam("type") int type,
                                                                       @RequestParam("maxTableId") int maxTableId,
                                                                       @RequestParam(value = "maxLikeCount", required = false) Integer maxLikeCount,
                                                                       @RequestParam(value = "userId", required = false) String userId,
                                                                       @RequestParam(value = "title", required = false) String title) throws Exception{
        if(!regularExpressionUtility.emailPatternMatch(userId))
            throw new EmailRegularExpressionNotMatchedException(userId + " is not matched by email format");

        if(title != null && title.length() > 50)
            throw new TooLongTitleException("Searcher's title request is too long");

        HashMap<String, Object> result = new HashMap<>();

        if(type == 1) { // 최신 순
            result.put("portfolioList", portfolioService.selectPortfolioListByLatest(maxTableId, userId, title));
        }else if(type == 2) { // 좋아요 순
            if(maxLikeCount == null)
                throw new NotExistLikeCountException("maxLikeCount cannot be null while like search");

            result.put("portfolioList", portfolioService.selectPortfolioListByLikeCount(maxTableId, maxLikeCount, userId, title));
        }
        else
            throw new NotVaildTypeException(type + " is not allowed in search method");

        result.put("status", 200);
        result.put("message", "OK");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
