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
    public ResponseEntity<HashMap<String, Object>> passwordLengthNotAllowedException(PasswordLengthNotAllowedException e) {
        log.info("PasswordLengthNotAllowedException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "password's length must be in 8 ~ 16");
        result.put("status", 400);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdDuplicatedException.class)
    public ResponseEntity<HashMap<String, Object>> idDuplicatedException (IdDuplicatedException e) {
        log.info("IdDuplicationException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "this id already existed");
        result.put("status", 409);
        return new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailRegularExpressionNotMatchedException.class)
    public ResponseEntity<HashMap<String, Object>> emailRegularExpressionNotMatchedException(EmailRegularExpressionNotMatchedException e) {
        log.info("EmailRegularExpressionNotMatchedException : "+ e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "id must be same as email format");
        result.put("status", 400);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginDeniedException.class)
    public ResponseEntity<HashMap<String, Object>> loginDeniedException(LoginDeniedException e) {
        log.info("LoginDeniedException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "id don't exist or password is not correct");
        result.put("status", 401);

        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RefreshTokenDifferentException.class)
    public ResponseEntity<HashMap<String, Object>> refreshTokenDiffrentException(RefreshTokenDifferentException e) {
        log.info("RefreshTokenDifferentException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "refresh token is not equal to server side refresh token");
        result.put("status", 401);

        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailAuthenticationFailedException.class)
    public ResponseEntity<HashMap<String, Object>> emailAuthenticationFailedException(EmailAuthenticationFailedException e) {
        log.info("EmailAuthenticationFailedException : " + e.getMessage());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "email token is already expired or not equal to server side");
        result.put("status", 401);

        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }
}
