package com.thunisoft.dzjz.listener;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 作者: guo yao
 * 日期: 2019年11月14日
 * 时间: 下午13:15
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.thunisoft.dzjz.anotation.External)")
    public void logPoint() {

    }

    @Before("logPoint()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
    }


    @AfterThrowing(pointcut = "logPoint()", throwing = "warn")
    public void doAfterThrow(Throwable warn) {
    }

    @AfterReturning(value = "logPoint()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {

    }

}
