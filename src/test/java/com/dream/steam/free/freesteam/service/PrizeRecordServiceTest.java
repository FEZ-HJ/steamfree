package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.dto.PrizeRecordDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2020/4/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PrizeRecordServiceTest {

    @Autowired
    private PrizeRecordService prizeRecordService;

    @Test
    public void findAllByPrizeId() {
        List<PrizeRecordDTO> a = prizeRecordService.findAllByPrizeId(1L,2,10);
        System.out.println(prizeRecordService.findAllByPrizeId(1L,2,10));
    }

    @Test
    public void countAllByPrizeId(){
        System.out.println(prizeRecordService.count(1L));
    }
}