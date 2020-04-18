package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.dto.PrizeRecordDTO;
import com.dream.steam.free.freesteam.entity.GiftContent;
import com.dream.steam.free.freesteam.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
