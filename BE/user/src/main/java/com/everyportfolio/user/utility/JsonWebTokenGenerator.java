package com.everyportfolio.user.utility;

import com.everyportfolio.user.DTO.AccessTokenDTO;
import com.everyportfolio.user.DTO.RefreshTokenDTO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenGenerator {
    private final Logger log = LoggerFactory.getLogger(JsonWebTokenGenerator.class);
    @Autowired
    private DateUtility dateUtility;

    public String generateAccessToken(String id, String auth) {
        AccessTokenDTO accessToken = new AccessTokenDTO(id, auth, null);
        accessToken.setExpired(dateUtility.addMintueByCurDate(30));

        return (new Gson()).toJson(accessToken);
    }

    public String generateRefreshToken(String id, String token) {
        RefreshTokenDTO refreshToken = new RefreshTokenDTO(id, null, token);
        refreshToken.setExpired(dateUtility.addWeekByCurDate(2));

        return (new Gson()).toJson(refreshToken);
    }
}
