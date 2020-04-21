package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.config.WxConfig;
import com.dream.steam.free.freesteam.dto.FreeGameDTO;
import com.dream.steam.free.freesteam.entity.LotteryContent;
import com.dream.steam.free.freesteam.entity.PrizeContent;
import com.dream.steam.free.freesteam.entity.PushForm;
import com.dream.steam.free.freesteam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by H.J
 * 2019/11/6
 */
@RestController
@RequestMapping("push")
public class PushFormController {

    @Autowired
    private PushFormService service;

    @Autowired
    private WxTemplateService templateService;

    @Autowired
    private FreeGameService freeGameService;

    @Autowired
    private PrizeContentService prizeContentService;

    @Autowired
    private WxConfig wxConfig;

    @PostMapping("insert")
    public void insert(@RequestBody PushForm pushForm){
        service.insert(pushForm);
    }

    /**
     * 推送新的限免信息
     */
    @GetMapping("pushFreeGame")
    public void pushFreeGame(Long id){
        List<PushForm> pushFormList = service.findAllCanPush();
        FreeGameDTO freeGameDTO = freeGameService.findById(id);
        List<String> pushData = new ArrayList<>();
        pushData.add(freeGameDTO.getChineseName());
        pushData.add("￥ " + freeGameDTO.getPrice());
        pushData.add("结束时间：" + freeGameDTO.getType().get(0));
        for(PushForm pushForm : pushFormList){
            templateService.push(pushForm,wxConfig.getFree_template_id(),"",pushData);
            pushForm.setUseDate(new Date());
            service.insert(pushForm);
        }
    }

    /**
     * 推送抽奖结果
     */
    @GetMapping("pushLottery")
    public void pushLottery(Long id){
        List<PushForm> pushFormList = service.findAllCanPush();
        PrizeContent prizeContent = prizeContentService.findById(id);
        List<String> pushData = new ArrayList<>();
        pushData.add(prizeContent.getTitle());
        pushData.add(prizeContent.getEndTime());
        pushData.add("请前往小程序查看详情！");
        for(PushForm pushForm : pushFormList){
            templateService.push(pushForm,wxConfig.getLottery_template_id(),"pages/lottery-details/index?id="+id,pushData);
            pushForm.setUseDate(new Date());
            service.insert(pushForm);
        }
    }
}
