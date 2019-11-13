package com.dream.steam.free.common.entity;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by H.J
 * 2019/11/13
 */
@Data
public class ImageCode {

    private String code;

    private LocalDateTime expireTime;

    private BufferedImage image;

    public ImageCode(String code, int expireIn, BufferedImage image) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
        this.image = image;
    }

    //判断验证码是否过期
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
