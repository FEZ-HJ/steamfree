package com.dream.steam.free.common.controller;

import com.dream.steam.free.freesteam.config.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by H.J
 * 2019/11/8
 */
@Controller

public class LoginController {

    @Autowired
    private WxConfig wxConfig;

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

    @RequestMapping("wxLogin")
    @ResponseBody
    public String wxLogin(String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+wxConfig.getAppid()+"&secret="+wxConfig.getSecret()+"&js_code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url,String.class);
        return forObject;
    }
}
