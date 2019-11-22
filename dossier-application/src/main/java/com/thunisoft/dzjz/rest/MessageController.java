package com.thunisoft.dzjz.rest;

import com.thunisoft.dzjz.anotation.External;
import com.thunisoft.dzjz.constant.ExtIdentifier;
import com.thunisoft.dzjz.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 作者: guo yao
 * 日期: 2019年11月01日
 * 时间: 下午18:51
 */
@Slf4j
@RestController
@RequestMapping(value = "/rest/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping("/send")
    public String send() {
        log.info("MessageController send start");
        messageService.sendMessage();
        log.info("MessageController send end");
        return "send success";
    }

    @GetMapping("/thread")
    public String thread() throws Throwable {
        log.info("MessageController thread start");
        // 多线程上下文切换
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("guo", "yao");
        messageService.threadContext();
        log.info("MessageController thread end");
        return "thread success";
    }

    @GetMapping("/executor")
    public String executor() {
        log.info("MessageController executor start");
        // 多线程上下文切换
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("guo", "yao");
        messageService.executorService();
        log.info("MessageController executor end");
        return "executor success";
    }

    @GetMapping(headers = "version=4.0", value = "/exception/{type}")
    public String exceptionV4(@PathVariable("type") Integer type) {
        log.info("MessageController executor start");
        messageService.exceptionDemo(type);
        log.info("MessageController executor end");
        return "exception success";
    }

    @External(ExtIdentifier.PETITION_LETTER)
    @GetMapping(headers = "version=4.1", value = "/exception/{type}")
    public String exceptionV41(@PathVariable("type") Integer type) {
        log.info("MessageController executor start");
        log.info("MessageController executor end");
        return "exceptionV41 success";
    }

    @External(ExtIdentifier.PETITION_LETTER)
    @DeleteMapping("/responseBody")
    public ResponseEntity<?> delete(@RequestBody(required = false) Map<String, Object> params) {
        System.err.println("===responseBodyMethod===" + params.toString());
        System.out.println();
        return null;
    }
}
