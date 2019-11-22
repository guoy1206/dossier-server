package com.thunisoft.dzjz.event;

import com.thunisoft.dzjz.domain.message.ProducerMessage;
import org.springframework.context.ApplicationEvent;

/**
 * 作者: guo yao
 * 日期: 2019年10月30日
 * 时间: 下午17:27
 */
public class ProducerMessageEvent extends ApplicationEvent {


    public ProducerMessageEvent(ProducerMessage message) {
        super(message);
    }
}
