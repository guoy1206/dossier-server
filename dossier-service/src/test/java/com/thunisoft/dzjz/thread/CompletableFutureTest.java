package com.thunisoft.dzjz.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 作者: guo yao
 * 日期: 2019年11月25日
 * 时间: 下午15:45
 */
public class CompletableFutureTest {

    private void normal1() throws InterruptedException {
        System.out.println("=== start ===");
        Thread.sleep(2000L);
        System.out.println("=== end ===");
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor;
        System.out.println("=== main start ===");
        CompletableFutureTest completableFutureTest = new CompletableFutureTest();
//        CompletableFuture.runAsync()
        System.out.println("===main end ===");
    }
}
