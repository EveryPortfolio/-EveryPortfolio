package com.everyportfolio.user;

import com.everyportfolio.user.DTO.RefreshTokenDTO;
import com.everyportfolio.user.mapper.RedisUserMapper;
import com.everyportfolio.user.mapper.UserMapper;
import com.everyportfolio.user.service.UserService;
import com.everyportfolio.user.utility.RandomUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RefreshTokenTests {

    /*@Test
    public void refreshToken테스트잘되나() {
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        RedisUserMapper redisUserMapper = Mockito.mock(RedisUserMapper.class);

        Mockito.when(userMapper.selectUserTokenById("11")).thenReturn("123");
        Mockito.when(redisUserMapper.findById("123")).thenReturn(Optional.ofNullable(null));

        UserService userService = new UserService(userMapper, redisUserMapper, new RandomUtility());
        RefreshTokenDTO refreshToken = new RefreshTokenDTO("11", null, "123");

        System.out.println(userService.compareRefreshToken(refreshToken));
        Assertions.assertEquals(userService.compareRefreshToken(refreshToken), true);

    }*/

}
