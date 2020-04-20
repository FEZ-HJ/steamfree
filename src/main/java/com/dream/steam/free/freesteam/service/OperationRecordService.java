package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.OperationRecord;
import com.dream.steam.free.freesteam.repository.OperationRecordRepository;
import com.dream.steam.free.SpringContextUtil;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2019/11/8
 */
@Service
public class OperationRecordService {

    public static void save(OperationRecord operationRecord){
        SpringContextUtil.getBean(OperationRecordRepository.class).save(operationRecord);
    }
}
