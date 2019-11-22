package com.thunisoft.dzjz.message.listener;

import com.thunisoft.dzjz.config.ThreadPoolConfig;
import com.thunisoft.dzjz.domain.message.ProducerMessage;
import com.thunisoft.dzjz.event.ProducerMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 作者: guo yao
 * 日期: 2019年10月30日
 * 时间: 下午17:23
 */
@Slf4j
@Component
public class ProducerMessageListener implements SmartApplicationListener {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass.equals(ProducerMessageEvent.class);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType.equals(ProducerMessage.class);
    }

    @Async(ThreadPoolConfig.ASYNC_EXECUTOR_NAME)
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        try {
            if (event.getSource() == null) {
                // 事件不存在
                return;
            }
            ProducerMessage message = (ProducerMessage) event.getSource();
            if (message.getTopic() == null) {
                // 类型为空
                return;
            }
            String topic = message.getTopic().getCode();
            if (message.getTag() != null) {
                topic += ":" + message.getTag().getCode();
            }
            log.info("开始发送消息：{}", message.getBody());
            rocketMQTemplate.convertAndSend(topic, message.getBody());
            // 添加rocketMQ日志
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送mq消息失败...");
        }
    }


}
