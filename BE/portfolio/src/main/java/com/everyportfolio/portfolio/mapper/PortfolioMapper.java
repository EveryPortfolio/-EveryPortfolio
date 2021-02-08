package com.everyportfolio.portfolio.mapper;

import com.everyportfolio.portfolio.model.Portfolio;
import com.everyportfolio.portfolio.model.PortfolioWithThumbnail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PortfolioMapper {
    void insertPortfolio(@Param("id") String id, @Param("title") String title, @Param("content") String content, @Param("templateType") int templateType);
    String selectPortfolioUserIdByTableId(@Param("tableId") int tableId);
    void updatePortfolioTitle(@Param("tableId") int tableId, @Param("title") String title);
    void updatePortfolioContent(@Param("tableId") int tableId, @Param("templateType") int templateType, @Param("content") String content);
    void deletePortfolio(@Param("tableId") int tableId);
    Portfolio selectPortfolioByTableId(@Param("tableId") int tableId);
    List<PortfolioWithThumbnail> selectPortfolioListByLatest(@Param("maxTableId") int maxTableId);
    List<PortfolioWithThumbnail> selectPortfolioListByLatestAndUserId(@Param("maxTableId") int maxTableId, @Param("userId") String userId);
    List<PortfolioWithThumbnail> selectPortfolioListByLatestAndTitle(@Param("maxTableId") int maxTableId, @Param("title") String title);
    List<PortfolioWithThumbnail> selectPortfolioListByLatestAndUserIdAndTitle(@Param("maxTableId") int maxTableId, @Param("userId") String userId, @Param("title") String title);

    List<PortfolioWithThumbnail> selectPortfolioListByLikeCount(@Param("maxTableId") int maxTableId, @Param("maxLikeCount") int maxLikeCount);
    List<PortfolioWithThumbnail> selectPortfolioListByLikeCountAndUserId(@Param("maxTableId") int maxTableId, @Param("maxLikeCount") int maxLikeCount, @Param("userId") String userId);
    List<PortfolioWithThumbnail> selectPortfolioListByLikeCountAndTitle(@Param("maxTableId") int maxTableId, @Param("maxLikeCount") int maxLikeCount, @Param("title") String title);
    List<PortfolioWithThumbnail> selectPortfolioListByLikeCountAndUserIdAndTitle(@Param("maxTableId") int maxTableId, @Param("maxLikeCount") int maxLikeCount, @Param("userId") String userId, @Param("title") String title);
}
