package com.everyportfolio.user.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    private String id;
    private String password;
}
