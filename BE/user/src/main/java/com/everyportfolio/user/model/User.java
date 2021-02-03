package com.everyportfolio.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("user")
@RedisHash("user")
public class User {
    @Id
    private String id;
    private String name;
    private String password;

}
