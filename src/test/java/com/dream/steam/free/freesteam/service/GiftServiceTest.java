package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.GiftContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2020/4/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GiftServiceTest {

    @Autowired
    private GiftService giftService;

    @Test
    public void findAll() {
        System.out.println(giftService.findAll());
    }

    @Test
    public void insert() {
        GiftContent giftContent = new GiftContent();
        giftContent.setTitle("好玩b");
        giftContent.setImg("https://media.st.dl.eccdnx.com/steam/apps/682990/header.jpg?t=1587070097");
        giftContent.setPrice(400);
        giftService.insert(giftContent);
    }
}