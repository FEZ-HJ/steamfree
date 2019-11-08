package com.dream.steam.free.common.service;

import com.dream.steam.free.common.entity.AdminUser;
import com.dream.steam.free.common.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by H.J
 * 2019/11/8
 */
public class AdminUserService implements UserDetailsService {

    @Autowired
    private AdminUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AdminUser user = repository.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("未查询到用户："+s+"信息");
        }
        return user;
    }
}
