package com.thunisoft.dzjz.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 作者: guo yao
 * 日期: 2019年08月13日
 * 时间: 下午20:24
 */
@Slf4j
public class ThreadPoolExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.out.println("------------->>> 捕获线程异常信息");
        log.info("Exception message - " + throwable.getMessage());
        log.info("Method name - " + method.getName());
        for (Object param : obj) {
            log.info("Parameter value - " + param);
        }
    }
}
