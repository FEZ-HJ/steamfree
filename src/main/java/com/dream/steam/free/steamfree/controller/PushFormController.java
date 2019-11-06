package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.entity.PushForm;
import com.dream.steam.free.steamfree.service.PushFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by H.J
 * 2019/11/6
 */
@RestController
@RequestMapping("push")
public class PushFormController {

    @Autowired
    private PushFormService service;

    @PostMapping("insert")
    public void insert(@RequestBody PushForm pushForm){
        service.insert(pushForm);
    }

    @GetMapping("push")
    public PushForm push(String openId){
        PushForm pushForm = service.findCanPush(openId);
        pushForm.setUseDate(new Date());
        service.insert(pushForm);
        return pushForm;
    }
}
