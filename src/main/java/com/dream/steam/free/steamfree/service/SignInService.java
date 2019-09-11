package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.dto.SignInDTO;
import com.dream.steam.free.steamfree.entity.Exp;
import com.dream.steam.free.steamfree.entity.SignInRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by H.J
 * 2019/9/9
 */
@Service
public class SignInService {

    @Autowired
    private SignInRecordService signInRecordService;

    @Autowired
    private ExpService expService;

//    签到方法
    @Transactional
    public SignInDTO signIn(String openId){
        SignInDTO signInDTO = new SignInDTO();
        List<String> signInRecords = signInRecordService.findList(openId);

        SignInRecord signInRecord = signInRecordService.save(openId);

//        已经签到过了
        if(signInRecord == null){
            Exp exp = expService.findByOpenId(openId);
            signInDTO.setSignInRecords(signInRecords);
            signInDTO.setExp(exp);
            return signInDTO;
        }

//        未签到过
        signInRecords.add(signInRecord.getData());
        Exp exp = expService.save(signInRecord.getContinuous(),openId);

        signInDTO.setSignInRecords(signInRecords);
        signInDTO.setExp(exp);

        return signInDTO;
    }

//    获取签到记录
    public SignInDTO getRecord(String openId){
        List<String> signInRecords = signInRecordService.findList(openId);
        Exp exp = expService.findByOpenId(openId);
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setSignInRecords(signInRecords);
        signInDTO.setExp(exp);
        return signInDTO;
    }

}
