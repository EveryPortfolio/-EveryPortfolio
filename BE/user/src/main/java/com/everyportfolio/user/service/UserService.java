package com.everyportfolio.user.service;

import com.everyportfolio.user.DTO.RefreshTokenDTO;
import com.everyportfolio.user.DTO.UserDTO;
import com.everyportfolio.user.mapper.UserMapper;
import com.everyportfolio.user.model.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserMapper userMapper;

    public void createUser(UserDTO user){
        userMapper.insertUser(user.toEntity());
    }

    public boolean signinUser(String id, String password) {
        User user = userMapper.selectUserById(id);

        if(user.getPassword().equals(password))
            return true;
        return false;
    }

    public void updateRefreshToken(String id, String token) {
        userMapper.updateUserTokenById(id, token);
    }

    public String getUserRefreshToken(String id) {
        return userMapper.selectUserTokenById(id);
    }

    public boolean compareRefreshToken(RefreshTokenDTO refreshToken) {
        if(getUserRefreshToken(refreshToken.getId()).equals(refreshToken.getToken()))
            return true;
        return false;
    }

}
