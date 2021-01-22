package com.everyportfolio.user.service;

import com.everyportfolio.user.DTO.RefreshTokenDTO;
import com.everyportfolio.user.DTO.UserDTO;
import com.everyportfolio.user.mapper.RedisUserMapper;
import com.everyportfolio.user.mapper.UserMapper;
import com.everyportfolio.user.model.RedisUser;
import com.everyportfolio.user.model.User;
import com.everyportfolio.user.utility.RandomUtility;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserMapper userMapper;
    private RedisUserMapper redisUserMapper;
    private RandomUtility randomUtility;

    public String createRedisUser(UserDTO user) {
        String token = randomUtility.generateRandomString(16);
        RedisUser temporaryUser = new RedisUser(user.getId(), user.getName(), user.getPassword(), token);
        redisUserMapper.save(temporaryUser);

        return token;
    }

    public User getRedisUserByToken(String id, String token) throws Exception {
        Optional<RedisUser> user = redisUserMapper.findById(id);

        if(!user.isPresent() || !user.get().getToken().equals(token))
            throw new Exception();

        return user.get().toUser();
    }

    public void createUser(UserDTO user){
        this.createUser(user.toEntity());
    }

    public void createUser(User user) {userMapper.insertUser(user);}

    public boolean loginUser(String id, String password) {
        User user = this.getUserById(id);

        if(user.getPassword().equals(password))
            return true;
        return false;
    }


    public boolean checkIdDuplication(String id) {
        if(this.getUserById(id) != null)
            return true;
        else {
            if(redisUserMapper.findById(id).isPresent())
                return true;
        }

        return false;
    }
    public User getUserById(String id) {
        return userMapper.selectUserById(id);
    }

    public void updateRefreshToken(String id, String token) {
        userMapper.updateUserTokenById(id, token);
    }

    public String getUserRefreshToken(String id) {
        return userMapper.selectUserTokenById(id);
    }

    public boolean compareRefreshToken(RefreshTokenDTO refreshToken) {
        if(refreshToken.getToken().equals(getUserRefreshToken(refreshToken.getId())))
            return true;
        return false;
    }

    public void deleteUserById(String id){
        userMapper.deleteUserById(id);
    }

}
