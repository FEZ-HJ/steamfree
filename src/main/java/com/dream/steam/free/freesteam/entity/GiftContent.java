package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by H.J
 * 2020/4/17
 */
@Data
@Entity
public class GiftContent {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String img;

    private int price;
}
