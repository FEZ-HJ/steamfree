package com.dream.steam.free.freesteam.entity;

import com.dream.steam.free.freesteam.utils.DateUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by H.J
 * 2020/4/17
 */
@Data
@Entity
public class PrizeDetailRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //    奖品ID
    private Long prizeId;

    //    用户ID
    private String openId;

    //    抽奖时间
    private String createTime;

    public PrizeDetailRecord(PrizeRecord prizeRecord){
        this.prizeId = prizeRecord.getPrizeId();
        this.openId = prizeRecord.getOpenId();
        this.createTime = DateUtil.getDate("yyyy-MM-dd HH:mm:ss");
    }

    public PrizeDetailRecord(){

    }
}
