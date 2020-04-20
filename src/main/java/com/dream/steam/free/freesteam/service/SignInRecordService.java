package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.OperationRecord;
import com.dream.steam.free.freesteam.entity.SignInRecord;
import com.dream.steam.free.freesteam.repository.SignInRecordRepository;
import com.dream.steam.free.freesteam.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
@Service
public class SignInRecordService {

    @Autowired
    private SignInRecordRepository signInRecordRepository;

//    查询本月的签到记录
    public List<String> findList(String openId){
        List<SignInRecord> list = signInRecordRepository.findByOpenIdAndDataLikeOrderByIdDesc(openId,DateUtil.getDate("yyyy-MM")+"%");
        List<String> signInRecords = new ArrayList<>();
        for(SignInRecord signInRecord : list){
            signInRecords.add(signInRecord.getData());
        }
        return signInRecords;
    }

//    签到
    public SignInRecord save(String openId){
//        今天的签到情况
        SignInRecord isSignIn = signInRecordRepository.findByOpenIdAndData(openId, DateUtil.getDate());

//        今天已有签到记录，不签到
        if(isSignIn != null){
            return null;
        }

//        昨天的签到情况
        SignInRecord previousSignIn = signInRecordRepository.findByOpenIdAndData(openId,DateUtil.getDateBefore(DateUtil.getDate(),-1));

        SignInRecord signInRecord = new SignInRecord(openId,DateUtil.getDate());

//         昨天有签到
        if(previousSignIn != null){
            signInRecord.setContinuous(previousSignIn.getContinuous() + 1);
//         昨天没签到
        }else{
            signInRecord.setContinuous(1);
        }

        OperationRecordService.save(new OperationRecord(openId,"签到"));
        return signInRecordRepository.save(signInRecord);
    }
}
