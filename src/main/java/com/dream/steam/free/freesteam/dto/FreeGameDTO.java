package com.dream.steam.free.freesteam.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
@Data
public class FreeGameDTO {

    private Long id;

    private String allAssess;

    private String assess;

    private String chineseName;

    private String content;

    private String englishName;

    private String factory;

    private List<String> images;

    private String language;

    private String newPrice;

    private String price;

    private String rowImage;

    private String saleDate;

    private List<String> tag;

    private List<String> type;
}
