package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.entity.OperationRecord;
import com.dream.steam.free.steamfree.repository.OperationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2019/11/8
 */
@Service
public class OperationRecordService {

    @Autowired
    private  OperationRecordRepository repository;

    public void save(OperationRecord operationRecord){
        repository.save(operationRecord);
    }
}
