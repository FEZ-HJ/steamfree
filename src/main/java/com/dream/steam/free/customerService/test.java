package com.dream.steam.free.customerService;

import org.apache.commons.lang3.StringUtils;
//import org.springframework.util.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Formatter;
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

//                 令牌
                String token = "yishanyishuihaofengjing";

                String[] strArray = new String[] { token, timestamp, nonce };
                Arrays.sort(strArray);
                String tmpStr = StringUtils.join(strArray);

                tmpStr = Sha1Util.sha1(tmpStr);

                System.out.println("----------" + tmpStr);
                System.out.println("----------" + signature);
                if (signature.equals(tmpStr)) {
                    System.out.println("++++++++++++++++++++++");
                    response.getOutputStream().write("true".getBytes());
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
    private String acceptMessage(HttpServletRequest request, HttpServletResponse response) {
//        {CreateTime=1548042266, Event=user_enter_tempsession, ToUserName=gh_e6198220cbff,
//                FromUserName=oZvme4q2Oi7Dz3FChXc43kqw28, MsgType=event, SessionFrom=wxapp}
        String respMessage = "";
        try {
            // xml请求解析
            Map<String, String> requestMap =null;
//            logger.info(">>>>>>>>>>>>>"+requestMap);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            String Event = requestMap.get("Event");  //SCAN 为扫描信息  VIEW 公众号底部点击事件
//            logger.info("fromUserName = "+fromUserName+" , ToUserName = "+toUserName+ " , msgType = "+msgType);

//            HmhWxTokenEntity tokenEntity = hmhWxTokenService.getHmhWxTokenInfoById(Long.parseLong(String.valueOf(1)));

            StringBuffer contentMsg = new StringBuffer();
            String html = "https://xxxxx.cn/index/wechat/index.html?oid="+fromUserName;
            String url = "☞ <a href=\""+html+"\">点击跳转</a>";
            //公众号关注事件消息
            if(msgType.equals("event")){
                contentMsg.append("↓↓您的专属充值链接↓↓").append("\n");
                contentMsg.append(url);
//                if(tokenEntity!=null){
                    testUtil.sendKfMessage(fromUserName,contentMsg.toString(),testUtil.getToken()); //把封装好的信息推送给用户
//                }
            }else if(msgType.equals("text")){
//                logger.info("公众号接受文字..........");
                contentMsg.append("↓↓您的专属充值链接↓↓").append("\n");
                contentMsg.append(url);
//                if(tokenEntity!=null){
                    testUtil.sendKfMessage(fromUserName,contentMsg.toString(),testUtil.getToken());
//                }
            }else if(msgType.equals("image")){
//                logger.info("公众号接受图片..........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}
