package com.everyportfolio.portfolio.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PortfolioMapper {
    void insertPortfolio(@Param("id") String id, @Param("title") String title, @Param("content") String content, @Param("templateType") int templateType);
    String selectPortfolioUserIdByTableId(@Param("tableId") int tableId);
}
