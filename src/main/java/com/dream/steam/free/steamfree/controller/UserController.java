package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.entity.User;
import com.dream.steam.free.steamfree.service.UserService;
import com.dream.steam.free.steamfree.utils.HTTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

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
    public User insert(User user) {
        return service.insert(user);
    }
}
