package com.everyportfolio.portfolio.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeListMapper {
    int selectLikeCountByTableId(@Param("tableId") int tableId);
    int selectLikeCountByTableIdAndUserId(@Param("tableId") int tableId, @Param("userId") String userId);
    void insertLikeList(@Param("tableId") int tableId, @Param("userId") String userId);
    void deleteLikeListByTableIdAndUserId(@Param("tableId") int tableId, @Param("userId") String userId);
}
