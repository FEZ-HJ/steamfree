package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.entity.HomePageImage;
import com.dream.steam.free.freesteam.service.HomePageImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by H.J
 * 2020/5/28
 */
@RestController
public class HomePageImageController {

    @Autowired
    private HomePageImageService service;

    @RequestMapping("homepage")
    public List<HomePageImage> findAllByOrderByIdAsc(){
        return service.findAllByOrderByIdAsc();
    }
}
