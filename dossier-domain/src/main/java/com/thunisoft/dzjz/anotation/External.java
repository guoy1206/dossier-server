package com.thunisoft.dzjz.anotation;

import com.thunisoft.dzjz.constant.ExtIdentifier;

import java.lang.annotation.*;

/**
 * 作者: guo yao
 * 日期: 2019年11月07日
 * 时间: 下午13:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface External {

    // 标识系统名称
    ExtIdentifier[] value();
}
