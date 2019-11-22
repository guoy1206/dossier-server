package com.thunisoft.dzjz.external;

import com.thunisoft.dzjz.anotation.External;
import com.thunisoft.dzjz.constant.ExtIdentifier;
import com.thunisoft.dzjz.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 作者: guo yao
 * 日期: 2019年11月05日
 * 时间: 下午19:38
 */
@Slf4j
@RestController
@RequestMapping("/materials")
public class MaterialsController {

    @Resource
    private MessageService messageService;

    @GetMapping("/send")
    public String send() {
        messageService.sendMessage();
        return "send success";
    }


    @External(value = {
            ExtIdentifier.PETITION_LETTER
    })
    @GetMapping(path = "/list")
    public String getMaterialsList() {
        //  业务逻辑
        return "success";
    }

    @External(value = {
            ExtIdentifier.PETITION_LETTER,
            ExtIdentifier.SHADOW_RESEARCH
    })
    @GetMapping(headers = "version=1.1", path = "/list")
    public String getMaterialsListV11() {
        //  业务逻辑
        return "success";
    }
}
