package com.everyportfolio.portfolio.service;

import com.everyportfolio.portfolio.mapper.LikeListMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class LikeListService {
    private LikeListMapper likeListMapper;

    public int selectLikeCountByTableId(int tableId) {
        return likeListMapper.selectLikeCountByTableId(tableId);
    }

    @Transactional
    public void switchLikeByTableId(int tableId, String userId) {
        if(likeListMapper.selectLikeCountByTableIdAndUserId(tableId, userId) == 1) {
            likeListMapper.deleteLikeListByTableIdAndUserId(tableId, userId);
        }
        else {
            likeListMapper.insertLikeList(tableId, userId);
        }
    }

    public boolean checkLikeByTableIdAndUserId(int tableId, String userId) {
        if(likeListMapper.selectLikeCountByTableIdAndUserId(tableId, userId) == 0)
            return false;
        return true;
    }
}
