package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.entity.PushForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by H.J
 * 2019/11/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushFormServiceTest {

    @Autowired
    private PushFormService service;

    @Test
    public void findAllCanPush() {
        List<PushForm> list = service.findAllCanPush(1l);
        System.out.println();
    }
}