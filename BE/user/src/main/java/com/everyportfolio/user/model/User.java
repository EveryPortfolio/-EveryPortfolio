package com.everyportfolio.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("user")
public class User {
    private String id;
    private String name;
    private String password;

}
