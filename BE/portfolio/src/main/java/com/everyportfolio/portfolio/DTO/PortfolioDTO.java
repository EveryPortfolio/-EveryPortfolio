package com.everyportfolio.portfolio.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PortfolioDTO {
    private String title;
    private String content;
    private int templateType;
}
