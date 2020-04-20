package com.chenms.juc;

import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        /**
         *与CountDownLatch一样，初始化将来要同步的线程数要与实际的相等
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("主线程调用开始...");
        for (int i = 0; i < 3; i++) {
            /**新开3个线程执行任务,加入这是2个线程，那么它们最后会因为达不到初始化的3个，而一直等待*/
            executorService.execute(new MyThread(cyclicBarrier));
        }
        System.out.println("主线程调用完毕...");
    }
}

class MyThread implements Runnable {
    private CyclicBarrier cyclicBarrier;

    /**
     * 参数传入
     */
    public MyThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1 + new Random().nextInt(3));
            System.out.println("玩家 " + Thread.currentThread().getName() + " 加入游戏,开始等待其它玩家...");
            /**
             * 到达栅栏时，可是等待其它线程，一直到调用await方法的线程个数达到new CyclicBarrier(3)初始化的个数后
             * 所有的线程才会接着向后运行，否则一直阻塞在这里
             */
            cyclicBarrier.await();
            System.out.println("所有玩家准备完毕,开始游戏...");
            TimeUnit.SECONDS.sleep(1 + new Random().nextInt(3));
            System.out.println("玩家 " + Thread.currentThread().getName() + " 正在大杀特杀...");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
