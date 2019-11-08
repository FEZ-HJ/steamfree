package com.dream.steam.free.steamfree.annotation;

import java.lang.annotation.*;

/**
 * Created by H.J
 * 2019/11/8
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationAnnotation {
    String value() default "";
    String openId() default "";
}
