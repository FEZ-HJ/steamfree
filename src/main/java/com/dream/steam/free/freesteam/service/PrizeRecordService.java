package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.PrizeRecord;
import com.dream.steam.free.freesteam.repository.PrizeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2020/4/16
 */
@Service
public class PrizeRecordService {

    @Autowired
    private PrizeRecordRepository repository;

    public PrizeRecord insert(PrizeRecord prizeRecord){
        return repository.save(prizeRecord);
    }

}
