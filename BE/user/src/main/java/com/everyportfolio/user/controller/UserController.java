package com.everyportfolio.user.controller;

import com.everyportfolio.user.DTO.AccessTokenDTO;
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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

@RestController
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
    public ResponseEntity<HashMap<String, Object>> signinUser(@RequestParam String id, @RequestParam String password) throws NoSuchAlgorithmException {
        HashMap<String, Object> result = new HashMap<>();
        HttpHeaders responseHeaders = new HttpHeaders();

        if(userService.signinUser(id, password)) {
            responseHeaders.add("access-token", jsonWebTokenGenerator.generateAccessToken(id, "USER"));

            byte[] token = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(token);

            String refreshTokenString = "";
            for(int i = 0; i < token.length; i++)
                refreshTokenString += String.format("%02X", token[i]);

            responseHeaders.add("refresh-token", jsonWebTokenGenerator.generateRefreshToken(id, refreshTokenString));
            userService.updateRefreshToken(id, refreshTokenString);

            result.put("status", "OK");
        }
        else
            result.put("status", "Reject");

        return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("refresh")
    public ResponseEntity<HashMap<String, Object>> refreshUser(@RequestHeader(value="refresh-token") String refreshTokenString) {
        RefreshTokenDTO refreshToken = (new Gson()).fromJson(refreshTokenString, RefreshTokenDTO.class);

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
    public ResponseEntity<HashMap<String, Object>> userProfile(@RequestHeader(value="access-token") String accessToken) {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", (new Gson()).fromJson(accessToken, AccessTokenDTO.class).getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
