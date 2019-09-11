package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.dto.SignInDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2019/9/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SignInServiceTest {

    @Autowired
    SignInService signInService;

    @Test
    public void signIn(){
        SignInDTO signInDTO = signInService.signIn("2");
        System.out.println(signInDTO);
    }
}