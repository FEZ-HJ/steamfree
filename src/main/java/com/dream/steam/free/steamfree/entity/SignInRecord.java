package com.dream.steam.free.steamfree.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by H.J
 * 2019/9/9
 */
@Data
@Entity
public class SignInRecord {

    public SignInRecord(){
        new SignInRecord("","");
    }

    public SignInRecord(String openId,String data){
        this.openId = openId;
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String openId;

    private String data;

//    连续签到天数
    private int continuous;


}
