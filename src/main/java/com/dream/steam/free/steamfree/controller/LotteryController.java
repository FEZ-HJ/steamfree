package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.dto.LotteryDTO;
import com.dream.steam.free.steamfree.entity.LotteryContent;
import com.dream.steam.free.steamfree.entity.LotteryRecord;
import com.dream.steam.free.steamfree.service.LotteryContentService;
import com.dream.steam.free.steamfree.service.LotteryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
@RestController
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

    @GetMapping("save")
    public List<LotteryRecord> save(LotteryRecord lotteryRecord){
        recordService.insert(lotteryRecord);
        return recordService.findAll(0,100,lotteryRecord.getUid());
    }
}
