package com.everyportfolio.user.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordChangeDTO {
    private String id;
    private String token;
    private String password;
}
