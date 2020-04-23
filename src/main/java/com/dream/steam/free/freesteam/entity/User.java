package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by H.J
 * 2019/10/30
 */
@Data
@Entity
public class User {

    @Id
    private String openId;

    private String avatarUrl;

    private String nickName;

    private Date modifyTime = new Date();

}
