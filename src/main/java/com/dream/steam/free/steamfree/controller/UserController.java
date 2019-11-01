package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.entity.User;
import com.dream.steam.free.steamfree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by H.J
 * 2019/10/30
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("insert")
    public User insert(@RequestBody User user) {
        return service.insert(user);
    }
}
