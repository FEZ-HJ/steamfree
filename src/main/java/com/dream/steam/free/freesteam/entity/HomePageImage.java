package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by H.J
 * 2020/5/28
 */
@Data
@Entity
public class HomePageImage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

//    图片地址
    private String image;

//    跳转路径
    private String address;
}
