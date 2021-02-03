package com.everyportfolio.portfolio.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PortfolioMapper {
    void insertPortfolio(@Param("id") String id, @Param("title") String title, @Param("content") String content, @Param("templateType") int templateType);
    String selectPortfolioUserIdByTableId(@Param("tableId") int tableId);
    void updatePortfolioTitle(@Param("tableId") int tableId, @Param("title") String title);
    void updatePortfolioContent(@Param("tableId") int tableId, @Param("templateType") int templateType, @Param("content") String content);
    void deletePortfolio(@Param("tableId") int tableId);
}
