package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.entity.GiftCdk;
import com.dream.steam.free.freesteam.entity.GiftContent;
import com.dream.steam.free.freesteam.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by H.J
 * 2020/4/17
 */
@RestController
@RequestMapping("gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    /**
     * 查询全部礼品
     */
    @GetMapping("list")
    public List<GiftContent> list(){
        return giftService.findAll();
    }

    /**
     * 兑换礼物
     */
    @GetMapping("convertGift")
    public Map<String,Object> convertGift(Long giftId, String openId){
        return giftService.convertGift(giftId,openId);
    }

    /**
     * 查询自己的兑换记录
     */
    @GetMapping("giftRecord")
    public List<GiftCdk> giftRecord(String openId){
        return giftService.giftRecord(openId) ;
    }
}
