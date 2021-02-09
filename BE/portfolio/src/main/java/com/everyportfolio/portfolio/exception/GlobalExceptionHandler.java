package com.everyportfolio.portfolio.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmailRegularExpressionNotMatchedException.class)
    public ResponseEntity<HashMap<String, Object>> emailRegularExpressionNotMatchedException(EmailRegularExpressionNotMatchedException e) {
        log.info("EmailRegularExpressionNotMatchedException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "userId must be same as email format");
        result.put("status", 400);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotVaildTypeException.class)
    public ResponseEntity<HashMap<String, Object>> notVaildTypeException(NotVaildTypeException e) {
        log.info("NotVaildTypeException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "type is not allowed. please choose 1(latest) or 2(like)");
        result.put("status", 400);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistLikeCountException.class)
    public ResponseEntity<HashMap<String, Object>> notExistLikeCountException(NotExistLikeCountException e) {
        log.info("NotExistLikeCountException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "you must fill in maxLikeCount while searching by like");
        result.put("status", 400);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistPortfolioException.class)
    public ResponseEntity<HashMap<String, Object>> notExistPortfolioException(NotExistPortfolioException e) {
        log.info("NotExistPortfolioException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "this portfolio doesn't exist");
        result.put("status", 400);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEqualToWriterException.class)
    public ResponseEntity<HashMap<String, Object>> notEqualToWriterException(NotEqualToWriterException e) {
        log.info("NotEqualToWriterException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "the requester is not equal to portfolio writer");
        result.put("status", 403);

        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TooLongThumbnailURLException.class)
    public ResponseEntity<HashMap<String, Object>> tooLongThumbnailURLException(TooLongThumbnailURLException e) {
        log.info("TooLongThumbnailURLException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "thumbnail url is too long. please write under 2048 characters");
        result.put("status", 400);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TooLongTitleException.class)
    public ResponseEntity<HashMap<String, Object>> tooLongTitleException(TooLongTitleException e) {
        log.info("TooLongTitleException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "title is too long. please write title under 50 characters");
        result.put("status", 400);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, Object>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "request is not valid. please check again");
        result.put("status", 400);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap<String, Object>> generalException(Exception e) {
        log.info("GeneralException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "Something's wrong in server. please try again");
        result.put("status", 500);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
