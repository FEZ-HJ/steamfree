package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.entity.PushForm;
import com.dream.steam.free.freesteam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 推送抽奖结果
     */
    @GetMapping("pushLottery")
    public void pushLottery(Long prizeId){
        service.push(prizeId);
    }
}
