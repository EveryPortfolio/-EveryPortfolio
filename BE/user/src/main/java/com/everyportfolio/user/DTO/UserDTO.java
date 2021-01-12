package com.everyportfolio.user.DTO;

import com.everyportfolio.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private String password;

    public User toEntity() {
        return new User(id, name, password);
    }
}
