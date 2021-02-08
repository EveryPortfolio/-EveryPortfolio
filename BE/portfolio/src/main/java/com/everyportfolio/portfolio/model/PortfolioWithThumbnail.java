package com.everyportfolio.portfolio.model;


import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("portfolioWithThumbnail")
public class PortfolioWithThumbnail {
    private int tableId;
    private String userId;
    private String title;
    private Date lastUpdateTime;
    private String thumbnailURL;
    private int likeCount;
}
