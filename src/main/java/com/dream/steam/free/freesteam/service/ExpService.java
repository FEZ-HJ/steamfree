package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.Exp;
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

    @Autowired
    private OperationRecordService service;

    public Exp findByOpenId(String openId){
        return expRepository.findByOpenId(openId);
    }

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
        service.save(new OperationRecord(openId,"增加积分：" + score));
        return expRepository.save(exp);
    }
}
