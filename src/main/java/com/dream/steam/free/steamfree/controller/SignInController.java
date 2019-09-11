package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.dto.SignInDTO;
import com.dream.steam.free.steamfree.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by H.J
 * 2019/9/10
 */
@RestController
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/in")
    public SignInDTO signIn(String openId){
        return signInService.signIn(openId);
    }

    @GetMapping("/getRecord")
    public SignInDTO getRecord(String openId){
        return signInService.getRecord(openId);
    }
}
