package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.utils.CustomerUtil;
import com.dream.steam.free.freesteam.utils.Sha1Util;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by H.J
 * 2020/11/11
 */
@RestController
public class GZHController {

    /**
     * 微信接口配置信息认证接口<br>
     * 需要正确响应微信发送的Token验证。 加密/校验流程如下：<br>
     * 1. 将token、timestamp、nonce三个参数进行字典序排序<br>
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密<br>
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @GetMapping("/gzh")
    public String cgi(HttpServletRequest request) throws Exception{
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//             微信加密签名
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
    }

    @PostMapping("/gzh")
    public String miniProgram(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
        response.setCharacterEncoding("utf-8");
        ServletInputStream stream = request.getInputStream();
        Document document = new SAXBuilder().build(stream);
        Element rootElement = document.getRootElement();
        if(rootElement.getChildText("MsgType").equals("text")){
            //回复 1 ，发送公众号二维码
            if("1".equals(rootElement.getChildText("Content"))){
                PrintWriter out = null;
                try{
                    out = response.getWriter();
//                    out.print(CustomerUtil.sendTextXML(rootElement,"长按识别二维码关注【steam限免助手】公众号！")+CustomerUtil.sendTextXML(rootElement,"长按识别二维码关注【steam限免助手】公众号！"));
                    out.print(CustomerUtil.sendImageXml(rootElement,"1.jpg"));
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }else{
                return CustomerUtil.sendService(rootElement);
            }
        }
        return "success";
    }
}
