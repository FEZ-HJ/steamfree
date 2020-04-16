package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 抽奖记录查询
 * Created by H.J
 * 2020/4/16
 */
@Data
@Entity
public class PrizeRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
//    奖品ID
    private Long prizeId;

//    用户ID
    private String openId;

//    参与抽奖的次数
    private int times;

//    最后抽奖的时间
    private String modifyTime;

}
