package com.dream.steam.free.steamfree.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2019/9/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SignInRecordServiceTest {

    @Autowired
    SignInRecordService signInRecordService;

    @Test
    public void findList() {
        System.out.println(signInRecordService.findList("obNf945tfBwIoR2k4M1nC18OA6ok1"));
    }

    @Test
    public void save() {
        String[] s = {"2019-09-09","2019-09-08","2019-09-06","2019-09-06","2019-09-05","2019-09-04","2019-09-03","2019-09-02","2019-09-01","2019-08-30","2019-08-28","2019-08-27","2019-08-20","2019-08-19","2019-08-18","2019-08-18","2019-08-18","2019-08-18","2019-08-18","2019-08-18","2019-08-18","2019-08-18","2019-08-17","2019-08-16","2019-08-16","2019-08-16","2019-08-16","2019-08-16","2019-08-16","2019-08-15","2019-08-06","2019-08-03","2019-07-31","2019-07-26","2019-07-24","2019-07-22","2019-07-20","2019-07-19","2019-07-17","2019-07-16","2019-07-12","2019-07-11","2019-07-10","2019-07-08","2019-07-07","2019-07-05","2019-07-04","2019-07-03","2019-07-02","2019-07-01","2019-06-29","2019-06-27","2019-06-23","2019-06-21","2019-06-20","2019-06-19","2019-06-18","2019-06-17"};
        for(String a : s){
//            signInRecordService.save("obNf945tfBwIoR2k4M1nC18OA6ok",a);
        }
    }
}