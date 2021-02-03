package com.everyportfolio.portfolio.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("portfolio")
public class Portfolio {
    private int tableId;
    private String userId;
    private String title;
    private String content;
    private Date lastUpdateTime;
}
