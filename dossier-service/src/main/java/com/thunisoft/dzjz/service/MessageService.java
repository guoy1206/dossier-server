package com.thunisoft.dzjz.service;

import com.thunisoft.dzjz.common.exception.ServiceException;
import com.thunisoft.dzjz.config.ThreadPoolConfig;
import com.thunisoft.dzjz.domain.message.ProducerMessage;
import com.thunisoft.dzjz.enums.ProducerTagEnum;
import com.thunisoft.dzjz.enums.ProducerTopicEnum;
import com.thunisoft.dzjz.enums.ResultCodeEnum;
import com.thunisoft.dzjz.event.ProducerMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.INTERNAL;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 生产者类
 * <p>
 * 作者: guo yao
 * 日期: 2019年10月30日
 * 时间: 下午15:38
 */
@Slf4j
@Service("messageService")
public class MessageService {


    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private ThreadPoolTaskExecutor asyncExecutor;


    public void sendMessage() {
        log.info("event send start");
        ProducerMessage message = new ProducerMessage();
        message.setTopic(ProducerTopicEnum.MATERIAL_GENERATE_PDF);
        message.setBody("开始转换拆料");
        message.setTag(ProducerTagEnum.UPDATE);
        ProducerMessageEvent messageEvent = new ProducerMessageEvent(message);
        applicationContext.publishEvent(messageEvent);
        log.info("event send end");
    }

    @Async(ThreadPoolConfig.ASYNC_EXECUTOR_NAME)
    public void threadContext() throws InterruptedException {
        log.info("获取上下文信息");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println("上下文信息是：" + request.getAttribute("guo"));
    }


    public void executorService() {
        log.info("executorService start");
        asyncExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String materialId = "";
                System.out.println("开始执行");
                try {
                    Thread.sleep(2000);
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    System.out.println("上下文信息是：" + request.getAttribute("guo"));
                    // 业务逻辑
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("转换PDF文件错误");
                    throw new ServiceException(ResultCodeEnum.MSG_NOT_READABLE, String.format("材料%s转换错误", materialId));
                }

            }
        });
        log.info("executorService end");
    }

    // 异常demo演示
    public void exceptionDemo(Integer type) {
        String userId = "007";
        switch (type) {
            case 1:
                throw new ServiceException(ResultCodeEnum.NOT_FOUND, String.format("User with id %s not found", userId));
            case 2:
                throw new ServiceException(String.format("User with id %s not found", userId));
            case 3:
                try {
                    // 业务代码
                    int temp = 1 / 0;
                } catch (Exception ex) {
                    System.err.println("catch exception");
                    throw new ServiceException(String.format("User with id %s not found", userId), ex);
                }
            case 4:
            default:
        }
    }

    // 增加消息
    public void createInfo(String name, Integer age) throws InterruptedException {
        log.info("MessageService create message " + Thread.currentThread().getName() + "|完成任务");
        // 模拟耗时1s
        Thread.sleep(1000L);
    }

    // 模拟查询数据
    public String queryInfo(String name) throws InterruptedException {
        log.info("MessageService query message " + Thread.currentThread().getName() + "|完成任务");
        // 模拟耗时1.5s
        Thread.sleep(1500L);
        return name;
    }

}
