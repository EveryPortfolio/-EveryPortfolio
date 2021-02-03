package com.everyportfolio.portfolio.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PortfolioContentDTO {
    private int tableId;
    private int templateType;
    private String content;
}
