package com.dream.steam.free.freesteam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by H.J
 * 2019/11/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxTemplateServiceTest {

    @Autowired
    private WxTemplateService service;

    @Test
    public void getAccess_token() {
        service.getAccess_token();
    }
}