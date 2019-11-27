package com.thunisoft.dzjz.dto;

import lombok.*;

/**
 * 作者: guo yao
 * 日期: 2019年11月26日
 * 时间: 下午19:22
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryResult {

    private String name;

    private Integer age;
}
