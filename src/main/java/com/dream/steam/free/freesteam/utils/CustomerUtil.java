package com.dream.steam.free.freesteam.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by H.J
 * 2020/4/24
 */
public class CustomerUtil {

    public static LocalCache localCache = new LocalCache();

    //客服消息推送地址
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxc78e9d02efadbba9&secret=5e2c9d2607b39653c92805fad551253a";
    public final static String send_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    public final static String upload_url = "https://api.weixin.qq.com/cgi-bin/media/upload?type=image&access_token=";

    /**
     * 获取微信token令牌
     */
    public static String  getToken(){
        Object value = localCache.get("wxkf_token");
        if(value == null){
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject(token_url,String.class);
            JSONObject jsonObject = JSONObject.parseObject(forObject);
            String wxkf_token = String.valueOf(jsonObject.get("access_token"));
            localCache.set("wxkf_token",wxkf_token,2*60*60*1000);
            value = localCache.get("wxkf_token");
        }
        return value.toString();
    }

    /**
     * 发送客服消息
     */
    public static ResponseEntity<String> sendKfMessage(JSONObject jsonObject){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(send_url+getToken(),jsonObject.toString(),String.class);
    }

    /**
     * 上传临时图片素材到微信服务器
     * 返回图片素材ID
     */
    public static String uploadTempMedia(String filePath,String fileName){
        RestTemplate restTemplate = new RestTemplate();

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath+"/"+fileName);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("filename",fileName);

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);

        String forObject = restTemplate.postForObject(upload_url+getToken(),files,String.class);
        JSONObject jsonObject = JSONObject.parseObject(forObject);
        return String.valueOf(jsonObject.get("media_id"));
    }

    /**
     * 从缓存获取素材ID
     */
    public static String getMediaId(String name) throws FileNotFoundException {
        Object value = localCache.get(name);
        if(value == null){
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "static/media";
            String mediaId = uploadTempMedia(filePath,name);
            localCache.set(name,mediaId,3*24*60*60*1000);
            value = localCache.get(name);
        }
        return value.toString();
    }

    /**
     * 发送图片客服消息
     */
    public static ResponseEntity<String> sendImage(JSONObject jsonObject,String fileName) throws FileNotFoundException {
        Map<String,Object> textMap = new HashMap<>();
        textMap.put("media_id", getMediaId(fileName));
        Map<String,Object> sendMap = new HashMap<>();
        sendMap.put("touser",jsonObject.getString("FromUserName"));
        sendMap.put("msgtype","image");
        sendMap.put("image",textMap);
        JSONObject jsonData = JSONObject.parseObject(JSON.toJSONString(sendMap));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(send_url+getToken(),jsonData.toString(),String.class);
    }

    /**
     * 发送文字客服消息
     */
    public static ResponseEntity<String> sendText(JSONObject jsonObject,String content)  {
        Map<String,Object> textMap = new HashMap<>();
        textMap.put("content", content);
        Map<String,Object> sendMap = new HashMap<>();
        sendMap.put("touser",jsonObject.getString("FromUserName"));
        sendMap.put("msgtype","text");
        sendMap.put("text",textMap);
        JSONObject jsonData = JSONObject.parseObject(JSON.toJSONString(sendMap));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(send_url+getToken(),jsonData.toString(),String.class);
    }

    /**
     * 发送文字客服消息
     */
    public static String sendService(JSONObject jsonObject) {
        Map<String,Object> sendMap = new HashMap<>();
        sendMap.put("ToUserName",jsonObject.getString("FromUserName"));
        sendMap.put("FromUserName","huang193921");
        sendMap.put("CreateTime",new Date().getTime() / 1000);
        sendMap.put("msgtype","transfer_customer_service");
        JSONObject jsonData = JSONObject.parseObject(JSON.toJSONString(sendMap));
        return jsonData.toString();
    }

    public static void main(String[] args) {
        long s = new Date().getTime();
        System.out.println(s);
    }

}
