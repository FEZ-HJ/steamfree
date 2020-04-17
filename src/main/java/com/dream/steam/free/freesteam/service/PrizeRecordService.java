package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.dto.PrizeRecordDTO;
import com.dream.steam.free.freesteam.entity.PrizeRecord;
import com.dream.steam.free.freesteam.repository.PrizeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    保存抽奖信息
    public PrizeRecord insert(PrizeRecord prizeRecord){
        PrizeRecord prizeRecordOld = repository.findByOpenIdAndPrizeId(prizeRecord.getOpenId(),prizeRecord.getPrizeId());
        if(prizeRecordOld != null){
            prizeRecordOld.setTimes(prizeRecordOld.getTimes() + 1);
        }else {
            prizeRecord.setTimes(1);
            prizeRecordOld = prizeRecord;
        }
        return repository.save(prizeRecordOld);
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
