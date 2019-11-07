package com.dream.steam.free.steamfree.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2019/11/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxConfigTest {

    @Autowired
    private WxConfig config;

    @Test
    public void test(){
        System.out.println(config.getAppid());
        System.out.println(config.getSecret());
    }
}