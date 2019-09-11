package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.entity.LotteryRecord;
import com.dream.steam.free.steamfree.repository.LotteryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
@Service
public class LotteryRecordService {

    @Autowired
    private LotteryRecordRepository repository;

    public LotteryRecord insert(LotteryRecord lotteryRecord){
        if(lotteryRecord.getId() == null){
            lotteryRecord.setTime(1);
        }else{
            lotteryRecord.setTime(lotteryRecord.getTime() + 1);
        }
        return repository.save(lotteryRecord);
    }

    public List<LotteryRecord> findAll(int page,int size,Long uid){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByUidOrderByTimeDesc(pageable,uid);
    }
}
