package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.config.WxConfig;
import com.dream.steam.free.freesteam.entity.PrizeContent;
import com.dream.steam.free.freesteam.entity.PushForm;
import com.dream.steam.free.freesteam.repository.PushFormRepository;
import com.dream.steam.free.freesteam.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by H.J
 * 2019/11/6
 */
@Service
public class PushFormService {

    @Autowired
    private PushFormRepository repository;

    @Autowired
    private PrizeContentService prizeContentService;

    @Autowired
    private WxTemplateService wxTemplateService;

    @Autowired
    private WxConfig wxConfig;

    public void insert(PushForm pushForm){
        repository.save(pushForm);
    }

//    查询可以推送的人员信息
    public List<PushForm> findAllCanPush(Long prizeId){
        return repository.findAllCanPush(prizeId);
    }

//    抽奖结果推送
    public void push(Long prizeId){
//        查询可以推送的人员
        List<PushForm> pushFormList = findAllCanPush(prizeId);
//        查询推送的奖品信息
        PrizeContent prizeContent = prizeContentService.findById(prizeId);
        Map<String,String> pushData = new HashMap<>();
        pushData.put("thing10",prizeContent.getTitle());
        pushData.put("date5",prizeContent.getEndTime());
        pushData.put("thing8","请前往小程序查看详情！");
        for(PushForm pushForm : pushFormList){
            wxTemplateService.push(pushForm,wxConfig.getLottery_template_id(),"pages/lottery-details/index?id="+prizeId,pushData);
            pushForm.setUseDate(new Date());
            insert(pushForm);
        }
    }
}
