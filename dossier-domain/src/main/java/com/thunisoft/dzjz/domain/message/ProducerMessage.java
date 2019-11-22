package com.thunisoft.dzjz.domain.message;

import com.thunisoft.dzjz.enums.ProducerTagEnum;
import com.thunisoft.dzjz.enums.ProducerTopicEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 作者: guo yao
 * 日期: 2019年10月30日
 * 时间: 下午19:01
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProducerMessage {

    // topic名称
    private ProducerTopicEnum topic;
    // tags 名称
    private ProducerTagEnum tag;
    // 消息体
    private String body;


}
