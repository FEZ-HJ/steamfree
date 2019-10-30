package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.entity.User;
import com.dream.steam.free.steamfree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2019/10/30
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User insert(User user){
        return repository.save(user);
    }
}
