package com.dream.steam.free.freesteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by H.J
 * 2019/11/8
 */
@Controller

public class LoginController {

    @RequestMapping("login")
    public String login(){
        return "/user/login";
    }
}
