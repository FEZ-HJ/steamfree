package com.dream.steam.free.steamfree.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by H.J
 * 2019/10/30
 */
public class HTTPUtil {

    public static String postTelentService(String urlStr, String urlParameter) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();

        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        urlcon.setDoOutput(true);
        // Read from the connection. Default is true.
        urlcon.setDoInput(true);
        // 默认是 GET方式
        urlcon.setRequestMethod("POST");

        // Post 请求不能使用缓存
        urlcon.setUseCaches(false);

        urlcon.setInstanceFollowRedirects(true);

        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        urlcon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。

        urlcon.connect();//获取连接

        DataOutputStream out = new DataOutputStream(urlcon.getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = urlParameter;
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);
        out.flush();
        out.close();

        InputStream is = urlcon.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        StringBuffer bs = new StringBuffer();
        String l = null;
        while((l=buffer.readLine())!=null){bs.append(l).append("/n");}
        String str1=bs.toString();
        str1  = new String(str1.getBytes("GBK"),"UTF-8");
        String str2=str1.substring(0,str1.length()-2);
        return str2;
    }
}
