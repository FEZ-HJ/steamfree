package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.User;
import com.dream.steam.free.freesteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2019/10/30
 */
@Service
public class UserService{

    @Autowired
    private UserRepository repository;

    public User insert(User user){
        return repository.save(user);
    }

    public User findByOpenId(String openId){
        return repository.findByOpenId(openId);
    }

}
