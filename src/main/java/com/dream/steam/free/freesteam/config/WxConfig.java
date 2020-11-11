package com.dream.steam.free.freesteam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by H.J
 * 2019/11/7
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {
    String appid;
    String secret;
    String free_template_id;
    String lottery_template_id;
    String gAppID;
    String gAppSecret;
}

