package com.dream.steam.free.steamfree.repository;

import com.dream.steam.free.steamfree.entity.PushForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by H.J
 * 2019/11/6
 */
public interface PushFormRepository extends JpaRepository<PushForm,String> {

    List<PushForm> findByOpenIdAndUseDateIsNullAndStaleDateIsAfterOrderByCreateDateAsc(String openId, Date date);
}
