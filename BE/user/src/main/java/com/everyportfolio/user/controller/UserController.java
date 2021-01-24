package com.everyportfolio.user.controller;

import com.everyportfolio.user.DTO.*;
import com.everyportfolio.user.exception.*;
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
    public ResponseEntity<HashMap<String, Object>> createUser(@RequestBody UserDTO user) throws Exception {
        if(userService.checkIdDuplication(user.getId()))
            throw new IdDuplicatedException(user.getId() + " is duplicated");

        if(!regularExpressionUtility.emailPatternMatch(user.getId()))
            throw new EmailRegularExpressionNotMatchedException(user.getId() + "is not matched with email regular expression");

        if(user.getPassword().length() < 8 || user.getPassword().length() > 16)
            throw new PasswordLengthNotAllowedException(user.getId() + "'s request is rejected");

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

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "OK");
        result.put("status", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("email-authentication")
    public ResponseEntity<HashMap<String, Object>> authenticateUserEmail(@RequestParam String params) throws Exception {
        HashMap<String, String> token = (new Gson()).fromJson(aes256Utility.decrypt(params), new TypeToken<HashMap<String, String>>(){}.getType());

        userService.createUser(userService.getRedisUserByToken(token.get("id"), token.get("token")));

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "OK");
        result.put("status", 200);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<HashMap<String, Object>> loginUser(@RequestBody LoginDTO login) throws Exception {
        if(login.getPassword().length() < 8 || login.getPassword().length() > 16)
            throw new PasswordLengthNotAllowedException(login.getId() + "'s request is rejected");

        login.setPassword(hashingUtility.generateHash(login.getPassword()));

        if(!userService.loginUser(login.getId(), login.getPassword()))
            throw new LoginDeniedException(login.getId() + "'s login request is denied");

        HttpHeaders responseHeaders = new HttpHeaders();


        responseHeaders.add("access-token", jsonWebTokenGenerator.generateAccessToken(login.getId(), "USER"));

        String refreshTokenString = randomUtility.generateRandomString(16);
        responseHeaders.add("refresh-token", jsonWebTokenGenerator.generateRefreshToken(login.getId(), refreshTokenString));
        userService.updateRefreshToken(login.getId(), refreshTokenString);

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "OK");
        result.put("status", 200);
        result.put("name", userService.getUserById(login.getId()).getName());

        return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("refresh")
    public ResponseEntity<HashMap<String, Object>> refreshUser(@RequestHeader(value="refresh-token") String refreshTokenString) throws Exception{
        RefreshTokenDTO refreshToken = (new Gson()).fromJson(refreshTokenString, RefreshTokenDTO.class);

        if(!userService.compareRefreshToken(refreshToken))
            throw new RefreshTokenDifferentException(refreshToken.getToken() + "is different");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("access-token", jsonWebTokenGenerator.generateAccessToken(refreshToken.getId(), "USER"));

        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "OK");
        result.put("status", 200);

        return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("profile")
    public ResponseEntity<HashMap<String, Object>> userProfile(@RequestHeader(value="access-token") String accessToken) {
        String id = (new Gson()).fromJson(accessToken, AccessTokenDTO.class).getId();
        String name = userService.getUserById(id).getName();

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "OK");
        result.put("id", id);
        result.put("name", name);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("check-id")
    public ResponseEntity<HashMap<String, Object>> userCheckId(@RequestParam String id) throws Exception{
        if(!regularExpressionUtility.emailPatternMatch(id))
            throw new EmailRegularExpressionNotMatchedException(id + "is not matched with email regular expression");

        if(userService.checkIdDuplication(id))
            throw new IdDuplicatedException(id + " is duplicated");

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "OK");
        result.put("status", 200);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<HashMap<String, Object>> userDelete(@RequestBody LoginDTO login) throws Exception{
        if(login.getPassword().length() < 8 || login.getPassword().length() > 16)
            throw new PasswordLengthNotAllowedException(login.getId() + "'s request is rejected");

        login.setPassword(hashingUtility.generateHash(login.getPassword()));
        if(!userService.loginUser(login.getId(), login.getPassword()))
            throw new LoginDeniedException(login.getId() + "'s login request is denied");


        userService.deleteUserById(login.getId());

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "OK");
        result.put("status", 200);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("logout")
    public ResponseEntity<HashMap<String, Object>> userLogout(@RequestHeader(value="access-token") String accessToken) {
        userService.updateRefreshToken((new Gson()).fromJson(accessToken, AccessTokenDTO.class).getId(), null);

        HashMap<String, Object> result = new HashMap<>();

        result.put("message", "OK");
        result.put("status", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("find-pwd")
    public ResponseEntity<HashMap<String, Object>> userFindPassword(@RequestBody String id) throws Exception{
        HashMap<String, String> json = (new Gson()).fromJson(id, new TypeToken<HashMap<String, String>>(){}.getType());
        id = json.get("id");

        if(!regularExpressionUtility.emailPatternMatch(id))
            throw new EmailRegularExpressionNotMatchedException(id + "is not matched with email regular expression");

        String token = userService.createRedisPasswordChange(id);

        emailService.setFrom("king7282@naver.com");
        emailService.setTo(id);
        emailService.setSubject("everyportfolio password authentication");
        emailService.setText("token : " + token);
        emailService.send();

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("message", "OK");
        result.put("status", 200);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("email-authentication-pwd")
    public ResponseEntity<HashMap<String, Object>> userEmailAuthenticationForPassword(@RequestParam String id, @RequestParam String token) throws Exception {

        if(!userService.compareTokenInRedisPasswordChange(id, token))
            throw new EmailAuthenticationFailedException(id + "is failed to email authentication while changing password");

        HashMap<String, Object> result = new HashMap<String, Object>();

        result.put("message", "OK");
        result.put("status", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping("change-pwd")
    public ResponseEntity<HashMap<String, Object>> userChangePassword(@RequestBody PasswordChangeDTO passwordChange) throws Exception{
        if(!regularExpressionUtility.emailPatternMatch(passwordChange.getId()))
            throw new EmailRegularExpressionNotMatchedException(passwordChange.getId() + "is not matched with email regular expression");

        if(!userService.compareTokenInRedisPasswordChange(passwordChange.getId(), passwordChange.getToken()))
            throw new EmailAuthenticationFailedException(passwordChange.getId() + "is failed to email authentication while changing password");

        if(passwordChange.getPassword().length() < 8 || passwordChange.getPassword().length() > 16)
            throw new PasswordLengthNotAllowedException(passwordChange.getId() + "'s request is rejected");

        passwordChange.setPassword(hashingUtility.generateHash(passwordChange.getPassword()));
        userService.updateUserPasswordById(passwordChange.getId(), passwordChange.getPassword());

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("message", "OK");
        result.put("status", 200);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
