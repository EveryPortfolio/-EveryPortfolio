package com.everyportfolio.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "user", timeToLive = 180L)
public class RedisPasswordChange {
    @Id
    private String id;
    private String token;
}
