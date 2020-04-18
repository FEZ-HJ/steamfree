package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.PrizeDetailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by H.J
 * 2020/4/17
 */
public interface PrizeDetailRecordRepository extends JpaRepository<PrizeDetailRecord,String> {

    @Query(nativeQuery = true,value = "SELECT open_id FROM prize_detail_record where prize_id = ?1 ORDER BY RAND() LIMIT 1")
    String winThePrize(Long prizeId);
}
