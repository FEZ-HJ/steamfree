package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by H.J
 * 2019/11/6
 */
@Data
@Entity
public class PushForm {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String openId;

    private Date createDate = new Date();

    private Date staleDate;

    private Date useDate;

    private String formId;

}
