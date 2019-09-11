package com.dream.steam.free.steamfree.dto;

import com.dream.steam.free.steamfree.entity.Exp;
import lombok.Data;

import java.util.List;


/**
 * Created by H.J
 * 2019/9/10
 */
@Data
public class SignInDTO {

    public List<String> signInRecords;
    public Exp exp;
}
