package com.thunisoft.dzjz.service;

import com.google.common.collect.Lists;
import com.thunisoft.dzjz.config.ThreadPoolConfig;
import com.thunisoft.dzjz.dto.QueryResult;
import com.thunisoft.dzjz.task.CreateTask;
import com.thunisoft.dzjz.task.QueryTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 多线程演示
 * <p>
 * 作者: guo yao
 * 日期: 2019年11月26日
 * 时间: 下午15:56
 */
@Slf4j
@Service("threadExampleService")
public class ThreadExampleService {

    @Resource
    private ThreadPoolTaskExecutor asyncExecutor;

    @Resource
    private MessageService messageService;

    private static final Integer COUNT = 10;


    // 注解标签异步 演示
    @Async(ThreadPoolConfig.ASYNC_EXECUTOR_NAME)
    public void methodExample() throws InterruptedException {
        log.info("methodExample start");
        TimeUnit.SECONDS.sleep(1);
        log.info("methodExample end");
    }

    // completionService 演示
    public void completionServiceExample() throws InterruptedException, ExecutionException, TimeoutException {
        log.info("completionServiceExample start");
        long starTime = System.currentTimeMillis();
        Random random = new Random();
        CompletionService<String> service = new ExecutorCompletionService<String>(asyncExecutor);
        for (int i = 0; i < COUNT; i++) {
            service.submit(() -> {
                Thread.sleep(2000L);
                log.info(Thread.currentThread().getName() + "|完成任务");
                return "data" + random.nextInt();
            });
        }
        for (int j = 0; j < COUNT; j++) {
            Future<String> take = service.take(); //这一行没有完成的任务就阻塞
            String result = take.get(); // 这一行在这里不会阻塞，引入放入队列中的都是已经完成的任务
            log.info("获取到结果：" + result);
        }
        long excTime = System.currentTimeMillis() - starTime;
        log.info("处理完成时间: {" + excTime + "} ms");
        log.info("completionServiceExample end");
    }

    // completionTask 演示
    public void completionTaskExample() throws InterruptedException, TimeoutException, ExecutionException {
        log.info("completionTaskExample start");
        long starTime = System.currentTimeMillis();
        CompletionService<QueryResult> service = new ExecutorCompletionService<QueryResult>(asyncExecutor);
        Random random = new Random();
        for (int j = 0; j < COUNT; j++) {
            QueryTask queryTask = new QueryTask(messageService, "刘德华", random.nextInt());
            service.submit(queryTask);
        }
        for (int j = 0; j < COUNT; j++) {
            QueryResult result = service.take().get(0, TimeUnit.SECONDS);
            log.info("获取到结果：name={},age={}", result.getName(), result.getAge());
        }
        long excTime = System.currentTimeMillis() - starTime;
        log.info("处理完成时间: {" + excTime + "} ms");
        log.info("completionTaskExample end");

    }

    // runnableTask 演示
    public void runnableTaskExample() {
        log.info("runnableTaskExample start");
        long starTime = System.currentTimeMillis();
        Random random = new Random();
        for (int j = 0; j < COUNT; j++) {
            CreateTask createTask = new CreateTask(messageService, "刘德华", random.nextInt());
            asyncExecutor.submit(createTask);
        }
        long excTime = System.currentTimeMillis() - starTime;
        log.info("处理完成时间: {" + excTime + "} ms");
        log.info("runnableTaskExample end");

    }

    // futureTask 演示
    public void futureTaskExample() throws ExecutionException, InterruptedException {
        log.info("futureTaskExample start");
        long starTime = System.currentTimeMillis();
        Callable<String> queryInfoCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return messageService.queryInfo("黎明");
            }
        };
        Runnable createInfoRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    messageService.createInfo("郭富城", new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        FutureTask<String> queryInfoFutureTask = new FutureTask<String>(queryInfoCallable);
//        FutureTask createInfoFutureTask = new FutureTask((Callable) createInfoRunnable);
//        Future<String> stringFuture = asyncExecutor.submit(queryInfoCallable);
        asyncExecutor.submit(queryInfoFutureTask);
        asyncExecutor.execute(createInfoRunnable);
//        asyncExecutor.execute(createInfoFutureTask);
        System.out.println("futureTaskExample result =" + queryInfoFutureTask.get());
        long excTime = System.currentTimeMillis() - starTime;
        log.info("处理完成时间: {" + excTime + "} ms");
        log.info("futureTaskExample end");
    }

    // CompletableFuture 演示
    public void completableFutureExample() throws InterruptedException {
        log.info("completableFutureExample start");
        Thread.sleep(2000L);
        log.info("completableFutureExample end");

    }

    // CountDownLatch 演示
    public void countDownLatchExample() throws InterruptedException {
        log.info("countDownLatchExample start");
        long starTime = System.currentTimeMillis();
        // 线程计数器
        final CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        // 线程池
//        ExecutorService exec = Executors.newFixedThreadPool(COUNT);
        // 循环执行10次
        for (int i = 0; i < COUNT; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 业务处理  模拟执行2s
                        Thread.sleep(2000);
                        log.info("开始执行业务.....");
                    } catch (Exception e) {
                        // 异常处理
                    } finally {
                        // 任务执行完成后，数量减一
                        countDownLatch.countDown();
                    }
                }
            };
            asyncExecutor.submit(runnable);
        }
        countDownLatch.await();
//        asyncExecutor.shutdown();
        long excTime = System.currentTimeMillis() - starTime;
        log.info("处理完成时间: {" + excTime + "} ms");
        log.info("countDownLatchExample end");
    }

}
