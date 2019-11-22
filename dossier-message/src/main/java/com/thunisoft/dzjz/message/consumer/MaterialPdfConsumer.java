package com.thunisoft.dzjz.message.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 作者: guo yao
 * 日期: 2019年10月30日
 * 时间: 下午19:44
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "material-generate-pdf", selectorExpression = "add", consumerGroup = "material")
public class MaterialPdfConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String body) {
        log.info("material message receive message tag update");
        System.err.println(body);

    }
}
