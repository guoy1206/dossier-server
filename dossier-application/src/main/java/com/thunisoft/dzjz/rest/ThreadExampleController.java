package com.thunisoft.dzjz.rest;

import com.thunisoft.dzjz.service.ThreadExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 作者: guo yao
 * 日期: 2019年11月26日
 * 时间: 下午17:36
 */
@Slf4j
@RestController
@RequestMapping(value = "/rest/thread")
public class ThreadExampleController {

    @Resource
    private ThreadExampleService threadExampleService;
    // 线程池
    @Resource
    private ThreadPoolTaskExecutor asyncExecutor;


    @GetMapping(path = "/method")
    public String methodExample() throws InterruptedException {
        log.info("methodExample controller start");
        threadExampleService.methodExample();
        log.info("methodExample controller end");
        return "success";
    }

    @GetMapping(path = "/completionService")
    public String completionServiceExample() throws InterruptedException, ExecutionException {
        log.info("completionServiceExample controller start");
        threadExampleService.completionServiceExample();
        log.info("completionServiceExample controller end");
        return "success";
    }

    @GetMapping(path = "/completionTask")
    public String completionTaskExample() throws InterruptedException, ExecutionException, TimeoutException {
        log.info("completionServiceExample controller start");
        threadExampleService.completionTaskExample();
        log.info("completionTask controller end");
        return "success";
    }

    @GetMapping(path = "/runnableTask")
    public String runnableTaskExample() {
        log.info("runnableTaskExample controller start");
        threadExampleService.runnableTaskExample();
        log.info("runnableTaskExample controller end");
        return "success";
    }


    @GetMapping(path = "/futureTask")
    public String futureTaskExample() throws ExecutionException, InterruptedException {
        log.info("futureTaskExample controller start");
        threadExampleService.futureTaskExample();
        log.info("futureTaskExample controller end");
        return "success";
    }


    @GetMapping(path = "/completableFuture")
    public String completableFutureExample() {
        log.info("completableFutureExample controller start");
        CompletableFuture.runAsync(() -> {
            try {
                threadExampleService.completableFutureExample();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, asyncExecutor);
        log.info("completableFutureExample controller end");
        return "success";
    }

    @GetMapping(path = "/countDownLatch")
    public String countDownLatchExample() throws InterruptedException {
        log.info("countDownLatchExample controller start");
        threadExampleService.countDownLatchExample();
        log.info("countDownLatchExample controller end");
        return "success";
    }


}
