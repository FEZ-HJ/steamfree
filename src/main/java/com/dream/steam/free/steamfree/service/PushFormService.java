package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.entity.PushForm;
import com.dream.steam.free.steamfree.repository.PushFormRepository;
import com.dream.steam.free.steamfree.utils.DateUtil;
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
        pushForm.setStaleDate(DateUtil.addDate(pushForm.getCreateDate(),7L));
        repository.save(pushForm);
    }

    public PushForm findCanPush(String openId){
        List<PushForm> list = repository.findByOpenIdAndUseDateIsNullAndStaleDateIsAfterOrderByCreateDateAsc(openId,new Date());
        if(list != null){
            return list.get(0);
        }
        else {
            return null;
        }
    }

    public List<PushForm> findAllCanPush(){
        return repository.findAllCanPush();
    }
}
