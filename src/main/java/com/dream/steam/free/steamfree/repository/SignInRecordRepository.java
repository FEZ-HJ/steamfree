package com.dream.steam.free.steamfree.repository;

import com.dream.steam.free.steamfree.entity.SignInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/9
 */
public interface SignInRecordRepository extends JpaRepository<SignInRecord,String> {

    SignInRecord findByOpenIdAndData(String openId,String data);

    List<SignInRecord> findByOpenIdAndDataLikeOrderByIdDesc(String openId,String data);

}
