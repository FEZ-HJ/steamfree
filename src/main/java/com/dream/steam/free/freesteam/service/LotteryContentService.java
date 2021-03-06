package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.LotteryContent;
import com.dream.steam.free.freesteam.repository.LotteryContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
@Service
public class LotteryContentService {

    @Autowired
    private LotteryContentRepository repository;

    public LotteryContent findById(Long id){
        return repository.findById(id);
    }

    public List<LotteryContent> findAll(int page , int size){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByOrderByIdDesc(pageable);
    }

    public LotteryContent insert(LotteryContent lotteryContent){
        return repository.save(lotteryContent);
    }

    public int count(){
        return repository.countAllBy();
    }

    @Modifying
    @Transactional
    public int delete(Long id){
        return repository.deleteById(id);
    }

    public List<LotteryContent> findUnderway(){
        return repository.findAllByOpenidOrderByIdDesc("");
    }
}
