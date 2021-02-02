package com.everyportfolio.portfolio.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessTokenDTO {
    private String id;
    private String auth;
    private Date expired;
}
