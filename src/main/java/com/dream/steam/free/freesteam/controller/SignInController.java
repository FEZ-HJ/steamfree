package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.dto.SignInDTO;
import com.dream.steam.free.freesteam.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by H.J
 * 2019/9/10
 */
@RestController
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping("/in")
    public SignInDTO signIn(String openId){
        return signInService.signIn(openId);
    }

    @GetMapping("/getRecord")
    public SignInDTO getRecord(String openId){
        return signInService.getRecord(openId);
    }
}
