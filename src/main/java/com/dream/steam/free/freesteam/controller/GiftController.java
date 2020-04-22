package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.dto.PrizeRecordDTO;
import com.dream.steam.free.freesteam.dto.ReturnJson;
import com.dream.steam.free.freesteam.entity.GiftCdk;
import com.dream.steam.free.freesteam.entity.GiftContent;
import com.dream.steam.free.freesteam.entity.PrizeContent;
import com.dream.steam.free.freesteam.service.GiftService;
import com.dream.steam.free.freesteam.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
     * 保存兑换礼品信息
     */
    @PostMapping("insert")
    public GiftContent insert(@RequestBody GiftContent giftContent){
        return giftService.insert(giftContent);
    }

    /**
     * 保存兑换码
     */
    @PostMapping("insertCdk")
    public GiftCdk insertCdk(@RequestBody GiftCdk giftCdk){
        return giftService.inset(giftCdk);
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

    /**
     * layui查询奖品信息接口
     */
    @GetMapping("layuiList")
    public Map<String,Object> content(int page, int limit){
        List<GiftContent> list = giftService.findAll();
        int count = giftService.count();
        return ReturnJson.RETURN("成功",list,count,0);
    }

    /**
     * layui cdk查询接口
     */
    @GetMapping("record")
    public Map<String,Object> record(Long id,int page, int limit){
        List<GiftCdk> list = giftService.record(id,page - 1,limit);
        int count = giftService.cdkCountById(id);
        return ReturnJson.RETURN("成功",list,count,0);
    }

    /**
     * 删除兑换礼品
     */
    @DeleteMapping("deleteContent")
    @Transactional
    public int deleteContent(Long id){
        giftService.deleteByGiftId(id);
        return giftService.deleteContent(id);
    }

    /**
     * 删除兑换礼品
     */
    @DeleteMapping("deleteCdk")
    @Transactional
    public int deleteCdk(Long id){
        return giftService.deleteCdk(id);
    }


}
