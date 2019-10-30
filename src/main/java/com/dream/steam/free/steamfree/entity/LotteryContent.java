package com.dream.steam.free.steamfree.entity;

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
public class LotteryContent {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

//   抽奖内容
    private String title;
    private String description;
    private String img;

//    中奖信息
    private String avatarUrl;
    private String openid;
    private String nickName;
    private String time;
    private String cdKey;

}
