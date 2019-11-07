package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.config.WxConfig;
import com.dream.steam.free.steamfree.dto.FreeGameDTO;
import com.dream.steam.free.steamfree.entity.LotteryContent;
import com.dream.steam.free.steamfree.entity.PushForm;
import com.dream.steam.free.steamfree.service.FreeGameService;
import com.dream.steam.free.steamfree.service.LotteryContentService;
import com.dream.steam.free.steamfree.service.PushFormService;
import com.dream.steam.free.steamfree.service.WxTemplateService;
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
    private LotteryContentService lotteryContentService;

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
        LotteryContent lotteryContent = lotteryContentService.findById(id);
        List<String> pushData = new ArrayList<>();
        pushData.add(lotteryContent.getTitle());
        pushData.add(lotteryContent.getDescription());
        pushData.add(lotteryContent.getNickName());
        for(PushForm pushForm : pushFormList){
            templateService.push(pushForm,wxConfig.getLottery_template_id(),"pages/lottery-details/index?id="+id,pushData);
            pushForm.setUseDate(new Date());
            service.insert(pushForm);
        }
    }
}
