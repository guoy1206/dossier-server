package com.thunisoft.dzjz.task;

import com.thunisoft.dzjz.dto.QueryResult;
import com.thunisoft.dzjz.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * 作者: guo yao
 * 日期: 2019年11月26日
 * 时间: 下午19:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryTask implements Callable<QueryResult> {

    private MessageService messageService;

    private String name;

    private Integer age;

    @Override
    public QueryResult call() throws Exception {
        QueryResult result = new QueryResult(messageService.queryInfo(this.name), age);
        System.out.println("result = " + result.toString());
        return result;
    }
}
