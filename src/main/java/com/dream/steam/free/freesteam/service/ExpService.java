package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.Exp;
import com.dream.steam.free.freesteam.entity.GiftContent;
import com.dream.steam.free.freesteam.entity.OperationRecord;
import com.dream.steam.free.freesteam.repository.ExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2019/9/10
 */
@Service
public class ExpService {

    @Autowired
    private ExpRepository expRepository;

    public Exp findByOpenId(String openId){
        return expRepository.findByOpenId(openId);
    }

//    签到增加积分
    public Exp save(int continuous,String openId){
        Long score;
        if(continuous > 6){
            score = 100L;
        }else{
            score = 50L;
        }
        Exp exp = findByOpenId(openId);
        if(exp == null) {
            exp = new Exp(openId);
        }
        exp.setCanUse(exp.getCanUse() + score);
        exp.setScore(exp.getScore() + score);
//        service.save(new OperationRecord(openId,"增加积分：" + score));
        return expRepository.save(exp);
    }

//    兑换奖品
    public Boolean save(String openId, GiftContent giftContent){
        Exp exp = findByOpenId(openId);
        if(exp.getCanUse() >= giftContent.getPrice()){
            exp.setCanUse(exp.getCanUse() - giftContent.getPrice());
            expRepository.save(exp);
            return true;
        }
        return false;
    }
}
