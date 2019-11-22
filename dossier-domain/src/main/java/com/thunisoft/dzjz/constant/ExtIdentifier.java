package com.thunisoft.dzjz.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务标识常量
 * <p>
 * 作者: guo yao
 * 日期: 2019年11月07日
 * 时间: 下午14:13
 */
@Getter
@AllArgsConstructor
public enum ExtIdentifier {

    SHADOW_RESEARCH("shadow_research", "影研"),

    PETITION_LETTER("petition_letter", "信访系统"),
    ;

    final String code;

    final String msg;
}
