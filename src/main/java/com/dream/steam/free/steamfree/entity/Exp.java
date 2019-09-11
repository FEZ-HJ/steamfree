package com.dream.steam.free.steamfree.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by H.J
 * 2019/9/9
 */
@Data
@Entity
public class Exp {

    @Id
    private String openId;

//    经验/总积分
    private Long score;

//    可用积分
    private Long canUse;

    public Exp(String openId){
        this.openId = openId;
        this.score = 0L;
        this.canUse = 0L;
    }

    public Exp(){
        new Exp("");
    }
}
