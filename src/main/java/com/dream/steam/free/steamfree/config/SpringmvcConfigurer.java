package com.dream.steam.free.steamfree.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H.J
 * 2019/10/31
 */
@Configuration
public class SpringmvcConfigurer extends WebMvcConfigurerAdapter {

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        supportedMediaTypes.add(MediaType.TEXT_HTML);
//
//        // 1、ByteArrayHttpMessageConverter
//        converters.add(new ByteArrayHttpMessageConverter());
//        // 2、UTF8HttpMessageConverter
//        UTF8HttpMessageConverter utf8Converter = new UTF8HttpMessageConverter();
//        utf8Converter.setSupportedMediaTypes(supportedMediaTypes);
//        converters.add(utf8Converter);
//        // 3、MappingJackson2HttpMessageConverter
//        // MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
//        // jacksonConverter.setPrefixJson(false);
//        // jacksonConverter.setSupportedMediaTypes(supportedMediaTypes);
//        // converters.add(jacksonConverter);
//        // 3、FastJsonHttpMessageConverter
//        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
//        fastJsonConverter.setSupportedMediaTypes(supportedMediaTypes);
//        fastJsonConverter.setFeatures(JsonUtils.features);
//        converters.add(fastJsonConverter);
//        super.extendMessageConverters(converters);
//    }
}
