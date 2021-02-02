package com.everyportfolio.portfolio.service;

import com.everyportfolio.portfolio.mapper.PortfolioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PortfolioService {
    private PortfolioMapper portfolioMapper;

    public void createProtfolio(String id, String title, String content, int templateType) {
        portfolioMapper.insertPortfolio(id, title, content, templateType);
    }
}
