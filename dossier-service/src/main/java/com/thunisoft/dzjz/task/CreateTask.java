package com.thunisoft.dzjz.task;

import com.thunisoft.dzjz.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: guo yao
 * 日期: 2019年11月27日
 * 时间: 下午9:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTask implements Runnable {

    private MessageService messageService;

    private String name;

    private Integer age;

    @Override
    public void run() {
        try {
            messageService.createInfo(this.name, this.age);
            System.out.println("==CreateTask create==" + this.age);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
