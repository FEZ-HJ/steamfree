package com.dream.steam.free.steamfree.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}
