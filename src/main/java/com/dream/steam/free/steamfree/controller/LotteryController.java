package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.dto.LotteryDTO;
import com.dream.steam.free.steamfree.dto.ReturnJson;
import com.dream.steam.free.steamfree.entity.LotteryContent;
import com.dream.steam.free.steamfree.entity.LotteryRecord;
import com.dream.steam.free.steamfree.service.LotteryContentService;
import com.dream.steam.free.steamfree.service.LotteryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by H.J
 * 2019/9/10
 */
@RestController
@RequestMapping("lottery")
public class LotteryController {

    @Autowired
    private LotteryContentService contentService;

    @Autowired
    private LotteryRecordService recordService;

    /**
     * 抽奖页信息 抽奖信息 抽奖记录
     */
    @GetMapping("index")
    public LotteryDTO index(Long id){
        LotteryDTO lotteryDTO = new LotteryDTO();
        LotteryContent lotteryContent = contentService.findById(id);
        List<LotteryRecord> records = recordService.findAll(0,100,id);
        lotteryDTO.setLotteryContent(lotteryContent);
        lotteryDTO.setRecords(records);
        return lotteryDTO;
    }

    /**
     * 保存抽奖记录
     * @return 所有用户的抽奖记录
     */
    @PostMapping("saveRecord")
    public List<LotteryRecord> saveRecord(LotteryRecord lotteryRecord){
        recordService.insert(lotteryRecord);
        return recordService.findAll(0,100,lotteryRecord.getUid());
    }

    /**
     * 保存奖品信息
     * @return 奖品信息
     */
    @PostMapping("saveContent")
    public LotteryContent saveContent(LotteryContent lotteryContent){
        return contentService.insert(lotteryContent);
    }

    /**
     * layui查询奖品信息接口
     */
    @GetMapping("content")
    public Map<String,Object> content(int page, int limit){
        List<LotteryContent> list = contentService.findAll(page - 1,limit);
        int count = contentService.count();
        return ReturnJson.RETURN("成功",list,count,0);
    }

    /**
     * 删除奖品
     */
    @DeleteMapping("deleteContent")
    @Transactional
    public int deleteContent(Long id){
        recordService.deleteAllByUid(id);
        return contentService.delete(id);
    }

    /**
     * 删除抽奖记录
     */
    @DeleteMapping("deleteRecord")
    public int deleteRecord(Long id){
        return recordService.deleteById(id);
    }

    /**
     * layui抽奖记录查询接口
     */
    @GetMapping("record")
    public Map<String,Object> record(Long id,int page, int limit){
        List<LotteryRecord> list = recordService.findAll(page - 1,limit,id);
        int count = recordService.count(id);
        return ReturnJson.RETURN("成功",list,count,0);
    }

    /**
     * 查询正在进行的抽奖
     */
    @GetMapping("findUnderway")
    public List<LotteryContent> findUnderway(){
        return contentService.findUnderway();
    }
}
