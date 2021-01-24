package com.everyportfolio.user.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    @NotNull
    private String id;
    @NotNull
    private String password;
}
