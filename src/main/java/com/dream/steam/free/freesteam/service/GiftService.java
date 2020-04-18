package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.GiftContent;
import com.dream.steam.free.freesteam.repository.GiftContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by H.J
 * 2020/4/17
 */
@Service
public class GiftService {

    @Autowired
    private GiftContentRepository repository;

    public List<GiftContent> findAll(){
        return repository.findAllByOrderByPriceAsc();
    }

    public GiftContent insert(GiftContent giftContent){
        return repository.save(giftContent);
    }
}
