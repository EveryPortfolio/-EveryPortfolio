package com.everyportfolio.user.controller;

import com.everyportfolio.user.DTO.RefreshTokenDTO;
import com.everyportfolio.user.DTO.UserDTO;
import com.everyportfolio.user.service.UserService;
import com.everyportfolio.user.utility.JsonWebTokenGenerator;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private JsonWebTokenGenerator jsonWebTokenGenerator;

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
        userService.createUser(user);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("signin")
    public ResponseEntity<HashMap<String, Object>> signinUser(@RequestBody UserDTO user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        HashMap<String, Object> result = new HashMap<>();
        HttpHeaders responseHeaders = new HttpHeaders();

        if(userService.signinUser(user.getId(), user.getPassword())) {
            responseHeaders.add("access-token", jsonWebTokenGenerator.generateAccessToken(user.getId(), "USER"));

            byte[] token = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(token);

            String refreshTokenString = "";
            for(int i = 0; i < token.length; i++)
                refreshTokenString += String.format("%02X", token[i]);

            responseHeaders.add("refresh-token", jsonWebTokenGenerator.generateRefreshToken(user.getId(), refreshTokenString));
            userService.updateRefreshToken(user.getId(), refreshTokenString);

            result.put("status", "OK");
        }
        else
            result.put("status", "Reject");

        return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("refresh")
    public ResponseEntity<HashMap<String, Object>> refreshUser(@RequestHeader(value="refresh-token") String refreshTokenString) {
        RefreshTokenDTO refreshToken = (new Gson()).fromJson(refreshTokenString, RefreshTokenDTO.class);
        log.info("receive refresh token : " + refreshToken.getToken());

        HashMap<String, Object> result = new HashMap<>();
        HttpHeaders responseHeaders = new HttpHeaders();

        if(userService.compareRefreshToken(refreshToken)) {
            result.put("status", "OK");
            responseHeaders.add("access-token", jsonWebTokenGenerator.generateAccessToken(refreshToken.getId(), "USER"));
        }
        else
            result.put("status", "Reject");

        return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("profile")
    public ResponseEntity<HashMap<String, Object>> userProfile(@RequestHeader HttpHeaders httpHeaders) {
        HashMap<String, Object> result = new HashMap<>();

        if(httpHeaders.getFirst("access-token") != null)
            result.put("id", httpHeaders.getFirst("access-token"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
