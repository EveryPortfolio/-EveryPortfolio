package com.everyportfolio.user.mapper;

import com.everyportfolio.user.model.RedisUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisUserMapper extends CrudRepository<RedisUser, String> {

}
