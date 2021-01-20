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
public class RedisUser {
    @Id
    private String id;
    private String name;
    private String password;
    private String token;

    public User toUser() {
        return new User(id, name, password);
    }

}
