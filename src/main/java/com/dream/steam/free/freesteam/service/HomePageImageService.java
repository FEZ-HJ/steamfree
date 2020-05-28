package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.HomePageImage;
import com.dream.steam.free.freesteam.repository.HomePageImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by H.J
 * 2020/5/28
 */
@Service
public class HomePageImageService {

    @Autowired
    private HomePageImageRepository repository;

    public List<HomePageImage> findAllByOrderByIdAsc(){
        return repository.findAllByOrderByIdAsc();
    }
}
