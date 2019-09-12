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

    @GetMapping("index")
    public LotteryDTO index(Long id){
        LotteryDTO lotteryDTO = new LotteryDTO();
        LotteryContent lotteryContent = contentService.findById(id);
        List<LotteryRecord> records = recordService.findAll(0,100,id);
        lotteryDTO.setLotteryContent(lotteryContent);
        lotteryDTO.setRecords(records);
        return lotteryDTO;
    }

    @PostMapping("saveRecord")
    public List<LotteryRecord> saveRecord(LotteryRecord lotteryRecord){
        recordService.insert(lotteryRecord);
        return recordService.findAll(0,100,lotteryRecord.getUid());
    }

    @PostMapping("saveContent")
    public LotteryContent saveContent(LotteryContent lotteryContent){
        return contentService.insert(lotteryContent);
    }

    @GetMapping("content")
    public Map<String,Object> content(int page, int limit){
        List<LotteryContent> list = contentService.findAll(page - 1,limit);
        int count = contentService.count();
        return ReturnJson.RETURN("成功",list,count,0);
    }

    @DeleteMapping("deleteContent")
    @Transactional
    public int deleteContent(Long id){
        recordService.deleteAllByUid(id);
        return contentService.delete(id);
    }

    @DeleteMapping("deleteRecord")
    public int deleteRecord(Long id){
        return recordService.deleteById(id);
    }

    @GetMapping("record")
    public Map<String,Object> record(Long id,int page, int limit){
        List<LotteryRecord> list = recordService.findAll(page - 1,limit,id);
        int count = recordService.count(id);
        return ReturnJson.RETURN("成功",list,count,0);
    }

}
