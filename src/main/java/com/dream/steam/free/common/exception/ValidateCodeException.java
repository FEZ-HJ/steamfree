package com.dream.steam.free.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by H.J
 * 2019/11/13
 * 用于抛出验证码错误的异常，集成AuthenticationException可被SpringSecurity捕获到
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
