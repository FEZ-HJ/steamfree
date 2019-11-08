package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by H.J
 * 2019/9/9
 */
@Data
@Entity
public class FreeGame {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String allAssess;

    private String assess;

    private String chineseName;

    @Column(columnDefinition="varchar(1000)")
    private String content;

    private String englishName;

    private String factory;

    @Column(columnDefinition="varchar(1000)")
    private String images;

    private String language;

    private String newPrice;

    private String price;

    private String rowImage;

    private String saleDate;

    private String tag;

    private String type;

}
