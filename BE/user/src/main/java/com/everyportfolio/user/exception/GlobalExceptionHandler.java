package com.everyportfolio.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PasswordLengthNotAllowedException.class)
    public ResponseEntity<HashMap<String, Object>> passwordLengthNotAllowedExcepton(PasswordLengthNotAllowedException e) {
        log.info("password's length is not allowed, " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "password's length must be in 8 ~ 16");
        result.put("status", 400);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

}
