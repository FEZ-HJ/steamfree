package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.Exp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by H.J
 * 2019/9/9
 */
public interface ExpRepository extends JpaRepository<Exp,String> {

    Exp findByOpenId(String openId);
}
