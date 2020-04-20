package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.OperationRecord;
import com.dream.steam.free.freesteam.repository.OperationRecordRepository;
import com.dream.steam.free.SpringUtil;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2019/11/8
 */
public class OperationRecordService {

    public static void save(OperationRecord operationRecord){
        SpringUtil.getBean(OperationRecordRepository.class).save(operationRecord);
    }
}
