package com.everyportfolio.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PasswordLengthNotAllowedException.class)
    public ResponseEntity<String> passwordLengthNotAllowedExcepton(PasswordLengthNotAllowedException e) {
        log.info("password's length is not allowed, " + e.getMessage());

        return new ResponseEntity<>("password is too short or long", HttpStatus.BAD_REQUEST);
    }

}
