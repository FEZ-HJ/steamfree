package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.LotteryRecord;
import com.dream.steam.free.freesteam.entity.PrizeRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by H.J
 * 2020/4/16
 */
public interface PrizeRecordRepository extends JpaRepository<PrizeRecord,String> {

//    查询所有的抽奖记录
    @Query(value = "select a.openId,a.times,b.nickName,b.avatarUrl from PrizeRecord a,User b where a.openId=b.openId and a.prizeId=?1 order by a.modifyTime desc")
    List<Object> findAllByPrizeId(Long prizeId, Pageable pageable);

//    查询抽奖记录总数
    int countAllByPrizeId(Long prizeId);

//    查询个人的抽奖情况
    PrizeRecord findByOpenIdAndPrizeId(String openId,Long prizeId);


}
