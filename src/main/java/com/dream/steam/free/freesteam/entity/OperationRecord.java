package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by H.J
 * 2019/11/8
 */
@Data
@Entity
public class OperationRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String openId;

    private Date createTime = new Date();

    private String operationName;

    public OperationRecord(){

    }

    public OperationRecord(String openId,String operationName){
        this.openId = openId;
        this.operationName = operationName;
    }
}
