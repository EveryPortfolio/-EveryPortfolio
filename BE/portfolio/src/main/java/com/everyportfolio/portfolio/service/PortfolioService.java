package com.everyportfolio.portfolio.service;

import com.everyportfolio.portfolio.mapper.PortfolioMapper;
import com.everyportfolio.portfolio.model.Portfolio;
import com.everyportfolio.portfolio.model.PortfolioWithThumbnail;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    @NonNull
    private PortfolioMapper portfolioMapper;
    @Value("${everyportfolio.thumbnail.default}")
    private String defaultThumbnailURL;

    public void createPortfolio(String id, String title, String content, int templateType, String thumbnailURL) {
        if(thumbnailURL == null)
            portfolioMapper.insertPortfolio(id, title, content, templateType, defaultThumbnailURL);
        else
            portfolioMapper.insertPortfolio(id, title, content, templateType, thumbnailURL);
    }

    public boolean compareUserIdWithCreator(String id, int tableId) {
        if(id.equals(portfolioMapper.selectPortfolioUserIdByTableId(tableId)))
            return true;
        return false;
    }

    public void updatePortfolioTitle(int tableId, String title) {
        portfolioMapper.updatePortfolioTitle(tableId, title);
    }

    public void updatePortfolioContent(int tableId, int templateType, String content) {
        portfolioMapper.updatePortfolioContent(tableId, templateType, content);
    }

    public void deletePortfolio(int tableId) {
        portfolioMapper.deletePortfolio(tableId);
    }

    public Portfolio selectPortfolioByTableId(int tableId) {
        return portfolioMapper.selectPortfolioByTableId(tableId);
    }

    public boolean existPortfolioByTableId(int tableId) {
        if(portfolioMapper.selectPortfolioUserIdByTableId(tableId) == null)
            return false;
        return true;
    }

    public List<PortfolioWithThumbnail> selectPortfolioListByLatest(int maxTableId, String userId, String title) {
        if(userId != null && title != null) {
            return portfolioMapper.selectPortfolioListByLatestAndUserIdAndTitle(maxTableId, userId, title);
        }else if(userId != null) {
            return portfolioMapper.selectPortfolioListByLatestAndUserId(maxTableId, userId);
        }else if(title != null) {
            return portfolioMapper.selectPortfolioListByLatestAndTitle(maxTableId, title);
        }else {
            return portfolioMapper.selectPortfolioListByLatest(maxTableId);
        }
    }

    public List<PortfolioWithThumbnail> selectPortfolioListByLikeCount(int maxTableId, int maxLikeCount, String userId, String title) {
        if(userId != null && title != null) {
            return portfolioMapper.selectPortfolioListByLikeCountAndUserIdAndTitle(maxTableId, maxLikeCount, userId, title);
        }else if(userId != null) {
            return portfolioMapper.selectPortfolioListByLikeCountAndUserId(maxTableId, maxLikeCount, userId);
        }else if(title != null) {
            return portfolioMapper.selectPortfolioListByLikeCountAndTitle(maxTableId, maxLikeCount, title);
        }else {
            return portfolioMapper.selectPortfolioListByLikeCount(maxTableId, maxLikeCount);
        }
    }
}
