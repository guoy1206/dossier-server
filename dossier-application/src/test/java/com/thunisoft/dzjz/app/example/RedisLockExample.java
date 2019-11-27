package com.thunisoft.dzjz.app.example;

import com.thunisoft.dzjz.DossierApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 作者: guo yao
 * 日期: 2019年11月27日
 * 时间: 下午14:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DossierApplication.class})
public class RedisLockExample {

    private static final String RETRY_REDISSON_LOCK_NAME = "MATERIALS_RETRY_REDIS_LOCK";

    long LOCK_WAIT_TIME = 1000L;

    // 锁持有超时时间:10分钟，防止线程在入锁以后，无限的执行下去，让锁无法释放
    long LOCK_TIME_OUT = 10 * 60 * 1000L;

    @Resource
    private RedissonClient redissonClient;

    // redis锁demo
    @Test
    public void redissonLockTest() {
        RLock redisLock = redissonClient.getLock(RETRY_REDISSON_LOCK_NAME);
        try {
            // 设置过期时间
            if (!redisLock.tryLock(LOCK_WAIT_TIME, LOCK_TIME_OUT, TimeUnit.MILLISECONDS)) {
                System.out.println("定时任务已经在其他节点执行中，定时任务取消!");
                return;
            }
            // 业务逻辑
            System.out.println("===业务执行===");
        } catch (Exception e) {
            // 异常处理
        } finally {
            try {
                redisLock.unlock();
            } catch (Exception e) {
                System.out.println("定时任务[材料重试]关闭锁异常");
            }
        }
    }

}
