package com.dream.steam.free.freesteam.entity;

import lombok.Data;

import java.util.Map;

/**
 * Created by H.J
 * 2019/11/7
 */
@Data
public class WxMssVo {
    private String touser;//用户openID
    private String template_id;//模版ID
    private String page = "pages/index/index";//默认跳到小程序页面
    private String form_id;//用户提交的formid
    private String emphasis_keyword = "keyword1.DATA";//放大的推送字段
    private Map<String,TemplateData> data;//推送文字

}
