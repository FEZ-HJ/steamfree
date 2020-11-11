package com.dream.steam.free.freesteam.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.steam.free.freesteam.utils.CustomerUtil;
import com.dream.steam.free.freesteam.utils.Sha1Util;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by H.J
 * 2020/4/24
 */
@RestController
public class CustomerController {

    /**
     * 微信接口配置信息认证接口<br>
     * 需要正确响应微信发送的Token验证。 加密/校验流程如下：<br>
     * 1. 将token、timestamp、nonce三个参数进行字典序排序<br>
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密<br>
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @RequestMapping("/cgi")
    public String cgi(HttpServletRequest request, HttpServletResponse response) throws Exception{
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        System.out.println("-------------" + isGet);
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        if (isGet) {
//                 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
//                随机字符串
            String echostr = request.getParameter("echostr");

//                 令牌
            String token = "yishanyishuihaofengjing";

//            将三个参数字符串拼接成一个字符串
            String[] strArray = new String[] { token, timestamp, nonce };
            Arrays.sort(strArray);
            String tmpStr = StringUtils.join(strArray);

//            进行sha1加密
            tmpStr = Sha1Util.sha1(tmpStr);

//            与signature对比
            if (signature.equals(tmpStr)) {
                return echostr;
            }else {
                return "";
            }
        }else{
            return acceptMessage(request, response);
        }
    }

    /**
     * 接受到微信接口数据
     * @param request
     * @param response
     * @return
     */
    private String acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
//        {"Content":"哈哈","CreateTime":1589892063,"ToUserName":"gh_dd7f4b53b189","FromUserName":"obNf945tfBwIoR2k4M1nC18OA6ok","MsgType":"text","MsgId":22761783588346819}
        ServletInputStream stream = request.getInputStream();
        Document document = new SAXBuilder().build(stream);
        Element rootElement = document.getRootElement();
        rootElement.getAttribute("MsgType");
        System.out.println(rootElement.getAttribute("MsgType"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            stringBuffer.append(line);
        }
        System.out.println("+++++++++++"+stringBuffer.toString());
        JSONObject jsonObject = JSONObject.parseObject(stringBuffer.toString());
        System.out.println("+++++++++++"+jsonObject.toString());
        if(jsonObject.getString("MsgType").equals("text")){
            Map<String,Object> textMap = new HashMap<>();
            //回复 1 ，发送公众号二维码
            if("1".equals(jsonObject.getString("Content"))){
                CustomerUtil.sendImage(jsonObject,"1.jpg");
                CustomerUtil.sendText(jsonObject,"长按识别二维码关注【steam限免助手】公众号！");
            }else{
                return CustomerUtil.sendService(jsonObject);
            }
        }
        return "success";
    }

}
