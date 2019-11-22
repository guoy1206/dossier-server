package com.thunisoft.dzjz.config;

import com.thunisoft.dzjz.async.ContextCopyingDecorator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * <p>
 * 作者: guo yao
 * 日期: 2019年08月13日
 * 时间: 下午18:19
 */
@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";

    @Bean(name = ASYNC_EXECUTOR_NAME)
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setTaskDecorator(new ContextCopyingDecorator());
        threadPool.setCorePoolSize(10);  // 核心线程数
        threadPool.setMaxPoolSize(10);   // 最大线程数
        threadPool.setQueueCapacity(100); // 线程队列数
        threadPool.setWaitForTasksToCompleteOnShutdown(true); // 待任务在关机时完成--表明等待所有线程执行完
        threadPool.setThreadNamePrefix("AsyncThread-");  // 线程名称前缀
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略
        threadPool.initialize(); // 初始化
        return threadPool;
    }

    /*
    其中我们主要注意的就是拒绝策略方法：setRejectedExecutionHandler()，拒绝策略常用有有这四种
    ThreadPoolExecutor.AbortPolicy 丢弃任务并抛出RejectedExecutionException异常(默认)。
    ThreadPoolExecutor.DiscardPolic 丢弃任务，但是不抛出异常。
    ThreadPoolExecutor.DiscardOldestPolicy 丢弃队列最前面的任务，然后重新尝试执行任务
    ThreadPoolExecutor.CallerRunsPolic 由调用线程处理该任务
    */


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, object) -> {
            log.error("===" + throwable.getMessage() + "===", throwable);
            log.error("exception method " + method.getName());
        };
//        return new ThreadPoolExceptionHandler();
    }
}
