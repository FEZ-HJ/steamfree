package com.dream.steam.free.common.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by H.J
 * 2019/11/8
 */
@Entity
@Data
public class RoleEntity {

    @Id
    private Long id;

    private String name;

}
