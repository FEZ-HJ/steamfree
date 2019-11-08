package com.dream.steam.free.freesteam.aspect;

import com.dream.steam.free.freesteam.annotation.OperationAnnotation;
import com.dream.steam.free.freesteam.entity.OperationRecord;
import com.dream.steam.free.freesteam.service.OperationRecordService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by H.J
 * 2019/11/8
 */
@Aspect
@Component
public class OperationAspect {

    @Autowired
    private OperationRecordService service;

    @Pointcut("@annotation(com.dream.steam.free.freesteam.annotation.OperationAnnotation)")
    public void annotationPointcut() {
    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        OperationAnnotation annotation = method.getAnnotation(OperationAnnotation.class);
        String value = annotation.value();
        String openId = annotation.openId();
        service.save(new OperationRecord(openId,value));
    }
}
