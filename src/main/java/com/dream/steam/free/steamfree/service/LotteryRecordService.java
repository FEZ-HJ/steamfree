package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.entity.LotteryRecord;
import com.dream.steam.free.steamfree.entity.OperationRecord;
import com.dream.steam.free.steamfree.repository.LotteryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
@Service
public class LotteryRecordService {

    @Autowired
    private LotteryRecordRepository repository;

    @Autowired
    private OperationRecordService service;

    public LotteryRecord insert(LotteryRecord lotteryRecord){
        if(lotteryRecord.getId() == null){
            LotteryRecord lotteryRecord1 = repository.findByOpenIdAndUid(lotteryRecord.getOpenId(),lotteryRecord.getUid());
            if(lotteryRecord1 == null){
                lotteryRecord.setTime(1);
            }else{
                lotteryRecord.setTime(lotteryRecord1.getTime()+1);
                lotteryRecord.setId(lotteryRecord1.getId());
            }
        }
        service.save(new OperationRecord(lotteryRecord.getOpenId(),"参与抽奖："+lotteryRecord.getUid() ));
        return repository.save(lotteryRecord);
    }

    public List<LotteryRecord> findAll(int page,int size,Long uid){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByUidOrderByTimeDesc(pageable,uid);
    }

    public int count(Long id){
        return repository.countAllById(id);
    }

    @Modifying
    @Transactional
    public int deleteById(Long id){
        return repository.deleteById(id);
    }

    @Modifying
    @Transactional
    public int deleteAllByUid(Long id){
        return repository.deleteAllByUid(id);
    }

    public LotteryRecord findById(Long id){
        return repository.findById(id);
    }

    public LotteryRecord findByOpenIdAndUid(String openId,Long uid){
        return repository.findByOpenIdAndUid(openId,uid);
    }
}
