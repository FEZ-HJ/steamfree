package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.PushForm;
import com.dream.steam.free.freesteam.repository.PushFormRepository;
import com.dream.steam.free.freesteam.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by H.J
 * 2019/11/6
 */
@Service
public class PushFormService {

    @Autowired
    private PushFormRepository repository;

    public void insert(PushForm pushForm){
        repository.save(pushForm);
    }

    public List<PushForm> findAllCanPush(){
        return repository.findAllCanPush();
    }
}
