package com.thunisoft.dzjz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;


/**
 * 定时任务描述枚举
 *
 * 作者: guo yao
 * 日期: 2019年11月05日
 * 时间: 下午21:13
 */
@Getter
@AllArgsConstructor
public enum WorkerCodeEnum {


    MATERIALS_CONVERT_RETRY_TRIGGER("materials_convert_retry_trigger", "材料转换重试任务"),

    DOSSIER_ADD_TAG_TRIGGER("dossier_add_tag_trigger", "卷宗打标签任务"),
    ;
    final String code;

    final String msg;
}
