package com.everyportfolio.portfolio.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeListMapper {
    int selectLikeCountByTableId(@Param("tableId") int tableId);
}
