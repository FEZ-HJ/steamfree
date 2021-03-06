package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.dto.PrizeRecordDTO;
import com.dream.steam.free.freesteam.entity.*;
import com.dream.steam.free.freesteam.repository.PrizeDetailRecordRepository;
import com.dream.steam.free.freesteam.repository.PrizeRecordRepository;
import com.dream.steam.free.freesteam.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H.J
 * 2020/4/16
 */
@Service
public class PrizeRecordService {

    @Autowired
    private PrizeRecordRepository repository;

    @Autowired
    private PrizeDetailRecordRepository prizeDetailRecordRepository;

    @Autowired
    private PrizeContentService prizeContentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PushFormService pushFormService;

//    保存抽奖信息
    @Transient
    public void insert(PrizeRecord prizeRecord){
//        检查是否到达抽奖人数
        PrizeContent prizeContent = prizeContentService.findById(prizeRecord.getPrizeId());
//        存入抽奖记录
        if(StringUtils.isEmpty(prizeContent.getEndTime())){
            PrizeRecord prizeRecordOld = repository.findByOpenIdAndPrizeId(prizeRecord.getOpenId(),prizeRecord.getPrizeId());
            if(prizeRecordOld != null){
                prizeRecordOld.setTimes(prizeRecordOld.getTimes() + 1);
                prizeRecordOld.setModifyTime(prizeRecord.getModifyTime());
            }else {
                prizeRecord.setTimes(1);
                prizeRecordOld = prizeRecord;
            }
            repository.save(prizeRecordOld);

            PrizeDetailRecord prizeDetailRecord = new PrizeDetailRecord(prizeRecord);
            prizeDetailRecordRepository.save(prizeDetailRecord);
            OperationRecordService.save(new OperationRecord(prizeRecord.getOpenId(),"参与抽奖：" + prizeContent.getId()));
        }

//        按人数开奖且没有中奖者时
        if("否".equals(prizeContent.getIsAd()) && StringUtils.isEmpty(prizeContent.getWinners())){
            int count = repository.countAllByPrizeId(prizeRecord.getPrizeId());
            if(count == (prizeContent.getTotal())){
//                查询中奖者信息
                String openId = prizeDetailRecordRepository.winThePrize(prizeRecord.getPrizeId());
                User user = userService.findByOpenId(openId);
                while (user == null){
                    openId = prizeDetailRecordRepository.winThePrize(prizeRecord.getPrizeId());
                    user = userService.findByOpenId(openId);
                }
//                保存中奖者信息
                prizeContent.setEndTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
                prizeContent.setWinners(openId);
                prizeContent.setAvatarUrl(user.getAvatarUrl());
                prizeContent.setNickName(user.getNickName());
                prizeContentService.insert(prizeContent);
//                推送抽奖结果
                pushFormService.push(prizeRecord.getPrizeId());
            }
        }
    }

    @Transient
    public void runLottery(Long prizeId){
//        查询奖品信息
        PrizeContent prizeContent = prizeContentService.findById(prizeId);
//        获取中奖者信息
        String openId = prizeDetailRecordRepository.winThePrize(prizeId);
        User user = userService.findByOpenId(openId);
        while (user == null){
            openId = prizeDetailRecordRepository.winThePrize(prizeId);
            user = userService.findByOpenId(openId);
        }
//        报存中奖者信息
        prizeContent.setEndTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        prizeContent.setWinners(openId);
        prizeContent.setAvatarUrl(user.getAvatarUrl());
        prizeContent.setNickName(user.getNickName());
        prizeContentService.insert(prizeContent);
//        推送抽奖结果
        pushFormService.push(prizeId);
    }

//    查询抽奖记录
    public List<PrizeRecordDTO> findAllByPrizeId(Long prizeId,int page,int size ){
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page,size);
        List<Object> list = repository.findAllByPrizeId(prizeId,pageable);
        List<PrizeRecordDTO> dtos = new ArrayList<>();
        for(Object o : list){
            Object[] ayyarO = (Object[]) o;
            PrizeRecordDTO prizeRecordDTO = new PrizeRecordDTO();
            prizeRecordDTO.setOpenId((String) ayyarO[0]);
            prizeRecordDTO.setTimes((Integer) ayyarO[1]);
            prizeRecordDTO.setNickName((String) ayyarO[2]);
            prizeRecordDTO.setAvatarUrl((String) ayyarO[3]);
            dtos.add(prizeRecordDTO);
        }
        return dtos;
    }

//    查询抽奖次数
    public int count(Long prizeId){
        return repository.countAllByPrizeId(prizeId);
    }

}
