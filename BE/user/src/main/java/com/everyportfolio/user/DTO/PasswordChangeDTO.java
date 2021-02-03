package com.everyportfolio.user.DTO;

import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordChangeDTO {
    @NotNull
    private String id;
    @NotNull
    private String token;
    @NotNull
    private String password;
}
