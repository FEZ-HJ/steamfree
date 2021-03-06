package com.dream.steam.free.freesteam.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jdom.CDATA;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by H.J
 * 2020/4/24
 */
public class CustomerUtil {

    public static Logger logger = LoggerFactory.getLogger(CustomerUtil.class);

    public static LocalCache localCache = new LocalCache();

    //客服消息推送地址
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf9de43e2411b1a2d&secret=544b24d1cebc55d2be5bbefe3d84cbb4";
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
            logger.info("获取token成功：" + forObject);
            JSONObject jsonObject = JSONObject.parseObject(forObject);
            String wxkf_token = String.valueOf(jsonObject.get("access_token"));
            logger.info("获取token成功：" + wxkf_token);
            localCache.set("wxkf_token",wxkf_token,2*60*60*1000);
            value = localCache.get("wxkf_token");
        }
        return value.toString();
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
        logger.info("上传Media成功：" + jsonObject.toString());
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
            logger.info("获取MediaID成功：" + value);
        }
        return value.toString();
    }

    /**
     * 发送图片客服消息
     */
    public static String sendImageXml(Element element,String fileName) throws FileNotFoundException {
        Element xml = new Element("xml");
        //2、一个ToUserName节点,以及节点内容,openID
        Element toUserName = new Element("ToUserName");
        toUserName.addContent(new CDATA(element.getChildText("FromUserName")));
        xml.addContent(toUserName);
        //3、FromUserName，开发者微信号
        Element fromUserName  = new Element("FromUserName");
        fromUserName.addContent(new CDATA(element.getChildText("ToUserName")));
        xml.addContent(fromUserName);
        //4、CreateTime消息创建时间
        Element createTime = new Element("CreateTime");
        createTime.addContent(System.currentTimeMillis() / 1000 + "");
        xml.addContent(createTime);
        //5、消息类型MsgType
        Element msgType = new Element("MsgType");
        msgType.addContent(new CDATA("image"));
        xml.addContent(msgType);
        //5、消息类型MsgType
        Element image = new Element("Image");
        Element media = new Element("MediaId");
        media.addContent(new CDATA(getMediaId(fileName)));
        image.addContent(media);
        xml.addContent(image);

        return xmlTOstring(xml);
    }

    /**
     * 发送文字客服消息
     */
    public static String sendTextXML(Element element,String content)  {
        Element xml = new Element("xml");
        //2、一个ToUserName节点,以及节点内容,openID
        Element toUserName = new Element("ToUserName");
        toUserName.addContent(new CDATA(element.getChildText("FromUserName")));
        xml.addContent(toUserName);
        //3、FromUserName，开发者微信号
        Element fromUserName  = new Element("FromUserName");
        fromUserName.addContent(new CDATA(element.getChildText("ToUserName")));
        xml.addContent(fromUserName);
        //4、CreateTime消息创建时间
        Element createTime = new Element("CreateTime");
        createTime.addContent(System.currentTimeMillis() / 1000 + "");
        xml.addContent(createTime);
        //5、消息类型MsgType
        Element msgType = new Element("MsgType");
        msgType.addContent(new CDATA("text"));
        xml.addContent(msgType);
        //5、消息类型MsgType
        Element Content = new Element("Content");
        Content.addContent(new CDATA(content));
        xml.addContent(Content);

//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        return restTemplate.postForEntity(send_url+getToken(),resultStr,String.class);
        return xmlTOstring(xml);
    }

    /**
     * 转到人工客服
     */
    public static String sendService(Element element) {
        Map<String,Object> sendMap = new HashMap<>();
        sendMap.put("ToUserName",element.getChildText("FromUserName"));
        sendMap.put("FromUserName",element.getChildText("ToUserName"));
        sendMap.put("CreateTime",element.getChildText("CreateTime"));
        sendMap.put("MsgType","transfer_customer_service");
        return JSON.toJSONString(sendMap);
    }

    public static String xmlTOstring(Element xml){
        String resultStr = "";
        try {
            //xml格式化成字符串
            Format format = Format.getCompactFormat();
            format.setEncoding("utf-8"); //设置格式化的字符集
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.output(xml, byteArrayOutputStream);
            resultStr = byteArrayOutputStream.toString("utf-8");
            //把节点Element，转换成一个字符串
            logger.info("拼接返回XML字符串成功：" + resultStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

}
