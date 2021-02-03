package com.everyportfolio.portfolio.service;

import com.everyportfolio.portfolio.mapper.LikeListMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeListService {
    private LikeListMapper likeListMapper;

    public int selectLikeCountByTableId(int tableId) {
        return likeListMapper.selectLikeCountByTableId(tableId);
    }
}
