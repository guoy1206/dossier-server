package com.thunisoft.dzjz.enums;

/**
 * 生产消息类型
 * <p>
 * 作者: guo yao
 * 日期: 2019年10月30日
 * 时间: 下午19:04
 */
public enum ProducerTopicEnum {

    MATERIAL_GENERATE_PDF("material-generate-pdf", "材料转换PDF"),
    MATERIAL_OCR_SUCCESS("material-ocr-success", "拆料OCR处理成功"),

    ;

    private String code;

    private String name;

    ProducerTopicEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
