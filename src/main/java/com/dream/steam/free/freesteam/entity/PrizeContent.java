package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by H.J
 * 2020/4/16
 */
@Data
@Entity
@Table(name = "PRIZE_CONTENT" ,schema = "steamfree")
public class PrizeContent {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "IMG")
    private String img;

    @Column(name = "IS_AD")
    private String isAd;

    @Column(name = "SECRET_KEY")
    private String secretKey;

    @Column(name = "TOTAL")
    private int total;

    @Column(name = "CREATE_TIME")
    private String createTime;

    @Column(name = "END_TIME")
    private String endTime;

    @Column(name = "WINNERS")
    private String winners;

}
