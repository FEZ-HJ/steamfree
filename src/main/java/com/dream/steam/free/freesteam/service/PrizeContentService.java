package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.LotteryContent;
import com.dream.steam.free.freesteam.entity.PrizeContent;
import com.dream.steam.free.freesteam.repository.PrizeContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by H.J
 * 2020/4/16
 */
@Service
public class PrizeContentService {

    @Autowired
    private PrizeContentRepository repository;

    public PrizeContent insert(PrizeContent prizeContent){
        return repository.save(prizeContent);
    }

    public PrizeContent findById(Long id){
        return repository.findById(id);
    }

    public List<PrizeContent> findAll(int page , int size){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByOrderByIdDesc(pageable);
    }

    public int count(){
        return repository.countAllBy();
    }

    @Modifying
    @Transactional
    public int delete(Long id){
        return repository.deleteById(id);
    }

    public List<PrizeContent> findAllByWinnersOrderByEndTimeDesc(String winners){
        return repository.findAllByWinnersOrderByEndTimeDesc(winners);
    }

}
