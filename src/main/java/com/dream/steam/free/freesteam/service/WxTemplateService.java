package com.dream.steam.free.freesteam.service;

import com.alibaba.fastjson.JSONObject;
import com.dream.steam.free.freesteam.config.WxConfig;
import com.dream.steam.free.freesteam.entity.PushForm;
import com.dream.steam.free.freesteam.entity.TemplateData;
import com.dream.steam.free.freesteam.entity.WxMssVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by H.J
 * 2019/11/7
 */
@Service
public class WxTemplateService {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private RestTemplate restTemplate;

    public String getAccess_token(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
            "&appid=" + wxConfig.getAppid() + "&secret=" + wxConfig.getSecret();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject pa = JSONObject.parseObject(json);
        return pa.getString("access_token");
    }

//    发起推送请求
    public String pushOneUser(WxMssVo wxMssVo){
        //获取access_token
        String access_token = getAccess_token();
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + access_token;
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }

//    包装推送的body
    public String push(PushForm pushForm, String templateId, String page,List<String> data){
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(pushForm.getOpenId());//用户openid
        wxMssVo.setForm_id(pushForm.getFormId());//formid
        if(!"".equals(page) && page != null){
            wxMssVo.setPage(page);
        }
        wxMssVo.setTemplate_id(templateId);//模版id

        Map<String, TemplateData> m = new HashMap<>();
        for(int i = 0 ; i < data.size() ; i++){
            TemplateData keyword = new TemplateData();
            keyword.setValue(data.get(i));
            m.put("keyword"+(i+1), keyword);
        }
        wxMssVo.setData(m);
        return pushOneUser(wxMssVo);
    }
}