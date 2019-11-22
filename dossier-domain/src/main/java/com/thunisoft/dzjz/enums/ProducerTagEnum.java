package com.thunisoft.dzjz.enums;

/**
 *
 * 作者: guo yao
 * 日期: 2019年10月30日
 * 时间: 下午19:49
 */
public enum ProducerTagEnum {

    CREATE("create", "增加"),
    UPDATE("update", "修改"),
    DELETE("delete", "删除"),

    ;

    private String code;

    private String name;

    ProducerTagEnum(String code, String name) {
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
