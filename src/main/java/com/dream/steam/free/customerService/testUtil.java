package com.dream.steam.free.customerService;


import com.alibaba.fastjson.JSONObject;
import com.dream.steam.free.freesteam.config.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by H.J
 * 2020/4/24
 */
public class testUtil {

    //客服消息推送地址
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxc78e9d02efadbba9&secret=5e2c9d2607b39653c92805fad551253a";
    public final static String kf_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

    public static String  getToken(){
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(token_url,String.class);
        JSONObject jsonObject = JSONObject.parseObject(forObject);
        return String.valueOf(jsonObject.get("access_token"));
    }

    public static ResponseEntity<String> sendKfMessage(JSONObject jsonObject){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(kf_url+"?access_token="+getToken(),jsonObject.toString(),String.class);
    }
}
