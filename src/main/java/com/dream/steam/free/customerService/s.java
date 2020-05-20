package com.dream.steam.free.customerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by H.J
 * 2020/5/20
 */
@RestController
public class s {

    public static LocalCache localCache = new LocalCache();

    @RequestMapping("s")
    public String s(){
        Object value = localCache.get("s");
        if(value == null){
            localCache.set("s",System.currentTimeMillis(),1000*10);
            value = localCache.get("s");
        }
        return value.toString();
    }
}
