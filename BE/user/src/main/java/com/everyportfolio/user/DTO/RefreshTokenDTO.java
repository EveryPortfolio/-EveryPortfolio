package com.everyportfolio.user.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RefreshTokenDTO {
    private String id;
    private Date expired;
    private String token;

}
