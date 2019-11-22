package com.thunisoft.dzjz.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 卷宗定时任务启动类
 * <p>
 * 作者: guo yao
 * 日期: 2019年08月01日
 * 时间: 下午20:44
 */
@SpringBootApplication
@Slf4j
public class DossierWorkerApplication {


    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(DossierWorkerApplication.class, args);
    }

}
