package com.everyportfolio.user.mapper;

import com.everyportfolio.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectUserById(String id);
    void insertUser(User user);
    void updateUserTokenById(String id, String token);
    String selectUserTokenById(String id);
    void deleteUserById(String id);
}
