package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by H.J
 * 2019/9/10
 */
@Data
@Entity
public class LotteryRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

//    奖品ID
    Long uid;

    String openId;

    String avatarUrl;

    String nickName;

    int time;
}
