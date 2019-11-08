package com.dream.steam.free.common.repository;

import com.dream.steam.free.common.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by H.J
 * 2019/11/8
 */
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {

    AdminUser findByUsername(String username);
}
