package com.dream.steam.free.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by H.J
 * 2019/11/8
 */
@Controller

public class LoginController {

    @RequestMapping("/")
    public String index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",authentication.getPrincipal());
        return "index";
    }

    @RequestMapping("/error_403")
    public String error_403(){
        return "error/403";
    }

    @RequestMapping("login")
    public String login(){
        return "/user/login";
    }


}
