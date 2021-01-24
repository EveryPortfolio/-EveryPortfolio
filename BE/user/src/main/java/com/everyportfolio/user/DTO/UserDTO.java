package com.everyportfolio.user.DTO;

import com.everyportfolio.user.model.User;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String password;

    public User toEntity() {
        return new User(id, name, password);
    }
}
