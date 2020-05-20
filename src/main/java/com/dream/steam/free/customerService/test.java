package com.dream.steam.free.customerService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by H.J
 * 2020/4/24
 */
@Controller
public class test {

    /**
     * 微信接口配置信息认证接口<br>
     * 需要正确响应微信发送的Token验证。 加密/校验流程如下：<br>
     * 1. 将token、timestamp、nonce三个参数进行字典序排序<br>
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密<br>
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @RequestMapping("/cgi")
    public void cgi(HttpServletRequest request, HttpServletResponse response) {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        try {
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

                String[] strArray = new String[] { token, timestamp, nonce };
                Arrays.sort(strArray);
                String tmpStr = StringUtils.join(strArray);

                tmpStr = Sha1Util.sha1(tmpStr);

                if (signature.equals(tmpStr)) {
                    response.getOutputStream().write(echostr.getBytes());
                }
            }else{
                // 进入POST聊天处理
                // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                // 接收消息并返回消息
                String result = acceptMessage(request, response);
                // 响应消息
                PrintWriter out = response.getWriter();
                out.print(result);
                out.close();
            }
        } catch (Exception ex) {
//            logger.error("微信帐号接口配置失败！", ex);
            ex.printStackTrace();
        }
    }

    /**
     * 接受到微信接口数据
     * @param request
     * @param response
     * @return
     */
    private String acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        {"Content":"哈哈","CreateTime":1589892063,"ToUserName":"gh_dd7f4b53b189","FromUserName":"obNf945tfBwIoR2k4M1nC18OA6ok","MsgType":"text","MsgId":22761783588346819}
        ServletInputStream stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            stringBuffer.append(line);
        }
        JSONObject jsonObject = JSONObject.parseObject(stringBuffer.toString());
        System.out.println("+++++++++++"+jsonObject.toString());
        if(jsonObject.getString("MsgType").equals("text")){
            Map<String,Object> textMap = new HashMap<>();
            textMap.put("content","ceshi");
            Map<String,Object> sendMap = new HashMap<>();
            sendMap.put("touser",jsonObject.getString("FromUserName"));
            sendMap.put("text",textMap);
            sendMap.put("msgtype","text");
            JSONObject jsonData = JSONObject.parseObject(JSON.toJSONString(sendMap));
            System.out.println(testUtil.sendKfMessage(jsonData));
        }
        return "success";
    }
}
