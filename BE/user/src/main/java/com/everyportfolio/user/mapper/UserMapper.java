package com.everyportfolio.user.mapper;

import com.everyportfolio.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectUserById(@Param("id") String id);
    void insertUser(User user);
    void updateUserTokenById(@Param("id") String id, @Param("token") String token);
    String selectUserTokenById(@Param("id") String id);
    void deleteUserById(@Param("id") String id);
}
