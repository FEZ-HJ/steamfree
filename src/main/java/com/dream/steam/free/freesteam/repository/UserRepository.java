package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by H.J
 * 2019/10/30
 */
public interface UserRepository extends JpaRepository<User,String> {

    User findByOpenId(String openId);


}
