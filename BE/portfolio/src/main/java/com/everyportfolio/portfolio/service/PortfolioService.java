package com.everyportfolio.portfolio.service;

import com.everyportfolio.portfolio.mapper.PortfolioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PortfolioService {
    private PortfolioMapper portfolioMapper;

    public void createPortfolio(String id, String title, String content, int templateType) {
        portfolioMapper.insertPortfolio(id, title, content, templateType);
    }

    public boolean compareUserIdWithCreator(String id, int tableId) {
        if(id.equals(portfolioMapper.selectPortfolioUserIdByTableId(tableId)))
            return true;
        return false;
    }

    public void updatePortfolioTitle(int tableId, String title) {
        portfolioMapper.updatePortfolioTitle(tableId, title);
    }
}
