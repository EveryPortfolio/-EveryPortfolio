package com.everyportfolio.user.mapper;

import com.everyportfolio.user.model.RedisPasswordChange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisPasswordChangeMapper extends CrudRepository<RedisPasswordChange, String> {

}
