package com.everyportfolio.api_gateway.filter;

import com.everyportfolio.api_gateway.DTO.AccessTokenDTO;
import com.everyportfolio.api_gateway.DTO.RefreshTokenDTO;
import com.everyportfolio.api_gateway.utility.AES256Utility;
import com.everyportfolio.api_gateway.utility.HMACUtility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;


@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    private Logger log = LoggerFactory.getLogger(GlobalFilter.class);

    @Autowired
    private AES256Utility aes256Utility;

    @Autowired
    private HMACUtility hmacUtility;

    @Autowired
    private Gson gson;

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    public GlobalFilter() {
        super(GlobalFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(GlobalFilter.Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            HttpHeaders httpRequestHeaders = request.getHeaders();

            if(httpRequestHeaders.getFirst("access-token") != null) {
                HashMap<String, String> token;
                AccessTokenDTO accessToken;
                String hmac, newHmac;

                try {
                    token = gson.fromJson(aes256Utility.decrypt(httpRequestHeaders.getFirst("access-token")), new TypeToken<HashMap<String, String>>(){}.getType());
                    newHmac = hmacUtility.generateHMAC(token.get("access-token"));
                }catch(Exception e) {
                    e.printStackTrace();
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "something was wrong, please try again");
                }

                try {
                    hmac = token.get("hmac");
                    accessToken = gson.fromJson(token.get("access-token"), AccessTokenDTO.class);
                }catch(Exception e) {
                    e.printStackTrace();
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "access-token was wrong. please reissue a access-token");
                }


                if(accessToken.getExpired().before(new Date())) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "access-token was expired");
                }

                if(!hmac.equals(newHmac)) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "this access-token was modified");
                }

                try {
                    request = request.mutate().header("access-token", gson.toJson(accessToken)).build();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            if(httpRequestHeaders.getFirst("refresh-token") != null) {
                HashMap<String, String> token;
                RefreshTokenDTO refreshToken;
                String hmac, newHmac;

                try {
                    token = gson.fromJson(aes256Utility.decrypt(httpRequestHeaders.getFirst("refresh-token")), new TypeToken<HashMap<String, String>>(){}.getType());
                    newHmac = hmacUtility.generateHMAC(token.get("refresh-token"));
                }catch(Exception e) {
                    log.error(e.getMessage());
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "something was wrong, please try again");
                }

                try {
                    hmac = token.get("hmac");
                    refreshToken = gson.fromJson(token.get("refresh-token"), RefreshTokenDTO.class);
                }catch(Exception e) {
                    log.info(e.getMessage());
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "refresh-token was wrong. please reissue a refresh-token");
                }


                if(refreshToken.getExpired().before(new Date())) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "refresh-token was expired");
                }

                if(!hmac.equals(newHmac)) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "this refresh-token was modified");
                }

                try {
                    request = request.mutate().header("refresh-token", gson.toJson(refreshToken)).build();
                } catch(Exception e) {
                    log.error(e.getMessage());
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "something was wrong, please try again");
                }
            }

            return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(()->{
                HttpHeaders httpResponseHeaders = exchange.getResponse().getHeaders();

                if(httpResponseHeaders.getFirst("access-token") != null) {
                    try {
                        HashMap<String, String> token = new HashMap<>();
                        token.put("access-token", httpResponseHeaders.getFirst("access-token"));
                        token.put("hmac", hmacUtility.generateHMAC(httpResponseHeaders.getFirst("access-token")));

                        httpResponseHeaders.set("access-token", aes256Utility.encrypt(gson.toJson(token)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if(httpResponseHeaders.getFirst("refresh-token") != null) {
                    try {
                        HashMap<String, String> token = new HashMap<>();
                        token.put("refresh-token", httpResponseHeaders.getFirst("refresh-token"));
                        token.put("hmac", hmacUtility.generateHMAC(httpResponseHeaders.getFirst("refresh-token")));

                        httpResponseHeaders.set("refresh-token", aes256Utility.encrypt(gson.toJson(token)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
        });
    }
}
