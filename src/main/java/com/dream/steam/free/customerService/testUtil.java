package com.dream.steam.free.customerService;


import com.alibaba.fastjson.JSONObject;
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
    public final static String kf_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

    public static String  getToken() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(kf_url,String.class);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(forObject);
        return String.valueOf(jsonObject.get("access_token"));
    }

    public static ResponseEntity<String> sendKfMessage(String openid, String text, String access_token)throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> map_content = new HashMap<>();
        map_content.put("content",text);
        Map<String,Object> map = new HashMap<>();
        map.put("touser",openid);
        map.put("msgtype","text");
        map.put("text",map_content);
        String content = JSONObject.toJSONString(map);
        return restTemplate.postForEntity(kf_url+"?access_token="+access_token,content,String.class);

    }
}
