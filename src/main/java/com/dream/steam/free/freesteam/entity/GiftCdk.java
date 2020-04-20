package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by H.J
 * 2020/4/20
 * 礼物仓库
 */
@Data
@Entity
public class GiftCdk {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long giftId;

    private String openId;

    private String cdk;

    private String createTime;

}
