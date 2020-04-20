package com.chenms.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest{
    public static void main(String[] args) {
        final CountDownLatch latch=new CountDownLatch(2);
        System.out.println("主线程开始执行…… ……");
        ExecutorService executorService1= Executors.newSingleThreadExecutor();

        executorService1.execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("子线程："+Thread.currentThread().getName()+"执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        executorService1.shutdown();

        ExecutorService executorService2= Executors.newSingleThreadExecutor();
        executorService2.execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("子线程："+Thread.currentThread().getName()+"执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        executorService2.shutdown();

        System.out.println("等待两个线程执行完毕…… ……");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}
