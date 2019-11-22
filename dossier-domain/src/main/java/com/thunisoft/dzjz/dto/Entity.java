package com.thunisoft.dzjz.dto;

import lombok.*;

/**
 * 作者: guo yao
 * 日期: 2019年11月14日
 * 时间: 下午12:41
 */
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    private Long uid;

    private String name;

    private Integer age;

}
