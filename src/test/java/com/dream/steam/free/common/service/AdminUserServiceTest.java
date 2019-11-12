package com.dream.steam.free.common.service;

import com.dream.steam.free.common.entity.AdminUser;
import com.dream.steam.free.common.repository.AdminUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminUserServiceTest {

    @Autowired
    AdminUserRepository service;

    @Test
    public void loadUserByUsername() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        AdminUser adminUser = new AdminUser();
        adminUser.setUsername("dream");
        adminUser.setPassword(passwordEncoder.encode("1"));
        service.save(adminUser);
    }
}