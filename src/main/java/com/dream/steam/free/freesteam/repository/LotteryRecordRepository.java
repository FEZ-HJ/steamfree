package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.LotteryRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
public interface LotteryRecordRepository extends JpaRepository<LotteryRecord,String> {

    LotteryRecord findByOpenIdAndUid(String openId,Long uid);

    List<LotteryRecord> findAllByUidOrderByTimeDesc(Pageable pageable,Long uid);

    LotteryRecord findById(Long id);

    int countAllById(Long id);

    int deleteById(Long id);

    int deleteAllByUid(Long id);

}
