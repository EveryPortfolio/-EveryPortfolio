package com.everyportfolio.user.controller;

import com.everyportfolio.user.DTO.*;
import com.everyportfolio.user.service.EmailService;
import com.everyportfolio.user.service.UserService;
import com.everyportfolio.user.utility.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

@AllArgsConstructor
@RestController
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private EmailService emailService;
    private JsonWebTokenGenerator jsonWebTokenGenerator;
    private RandomUtility randomUtility;
    private AES256Utility aes256Utility;
    private RegularExpressionUtility regularExpressionUtility;
    private HashingUtility hashingUtility;

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) throws Exception {
        if(userService.checkIdDuplication(user.getId()) || !regularExpressionUtility.emailPatternMatch(user.getId()) || user.getPassword().length() > 16 || user.getPassword().length() < 8)
            throw new Exception();

        user.setPassword(hashingUtility.generateHash(user.getPassword()));

        HashMap<String, String> token = new HashMap<>();
        token.put("token", userService.createRedisUser(user));
        token.put("id", user.getId());

        String params = URLEncoder.encode(aes256Utility.encrypt((new Gson()).toJson(token)), "UTF-8");

        emailService.setFrom("king7282@naver.com");
        emailService.setTo(user.getId());
        emailService.setSubject("everyportfolio sign up authentication");
        emailService.setText("https://everyportfolio.com/user/email-authentication?params=" + params);
        emailService.send();

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("email-authentication")
    public ResponseEntity<String> authenticateUserEmail(@RequestParam String params) throws Exception {
        HashMap<String, String> token = (new Gson()).fromJson(aes256Utility.decrypt(params), new TypeToken<HashMap<String, String>>(){}.getType());

        userService.createUser(userService.getRedisUserByToken(token.get("id"), token.get("token")));

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO login) throws Exception {
        if(login.getPassword().length() < 8 || login.getPassword().length() > 16)
            throw new Exception();

        login.setPassword(hashingUtility.generateHash(login.getPassword()));

        HttpHeaders responseHeaders = new HttpHeaders();

        if(userService.loginUser(login.getId(), login.getPassword())) {

            responseHeaders.add("access-token", jsonWebTokenGenerator.generateAccessToken(login.getId(), "USER"));

            String refreshTokenString = randomUtility.generateRandomString(16);
            responseHeaders.add("refresh-token", jsonWebTokenGenerator.generateRefreshToken(login.getId(), refreshTokenString));
            userService.updateRefreshToken(login.getId(), refreshTokenString);

            return new ResponseEntity<>(userService.getUserById(login.getId()).getName(), responseHeaders, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Reject", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("refresh")
    public ResponseEntity<HashMap<String, Object>> refreshUser(@RequestHeader(value="refresh-token") String refreshTokenString) {
        RefreshTokenDTO refreshToken = (new Gson()).fromJson(refreshTokenString, RefreshTokenDTO.class);

        HashMap<String, Object> result = new HashMap<>();
        HttpHeaders responseHeaders = new HttpHeaders();

        if(userService.compareRefreshToken(refreshToken)) {
            result.put("status", "OK");
            responseHeaders.add("access-token", jsonWebTokenGenerator.generateAccessToken(refreshToken.getId(), "USER"));
            return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("profile")
    public ResponseEntity<HashMap<String, Object>> userProfile(@RequestHeader(value="access-token") String accessToken) {
        HashMap<String, Object> result = new HashMap<>();
        String id = (new Gson()).fromJson(accessToken, AccessTokenDTO.class).getId();
        result.put("id", id);
        result.put("name", userService.getUserById(id).getName());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("check-id")
    public ResponseEntity<String> userCheckId(@RequestParam String id) {
        if(regularExpressionUtility.emailPatternMatch(id) && !userService.checkIdDuplication(id)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Reject", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> userDelete(@RequestBody LoginDTO login) throws Exception{
        if(login.getPassword().length() < 8 || login.getPassword().length() > 16)
            throw new Exception();

        login.setPassword(hashingUtility.generateHash(login.getPassword()));

        if(userService.loginUser(login.getId(), login.getPassword())) {
            userService.deleteUserById(login.getId());
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Reject", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("logout")
    public ResponseEntity<String> userLogout(@RequestHeader(value="access-token") String accessToken) throws Exception {
        userService.updateRefreshToken((new Gson()).fromJson(accessToken, AccessTokenDTO.class).getId(), null);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
