package com.tw.harper.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hbowang on 11/1/16.
 */
public class CountDownLathDemo implements Runnable{
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLathDemo demo = new CountDownLathDemo();
    @Override
    public void run() {
        //模拟检查任务
        try {
            Thread.sleep(1000);
            System.out.println("check completed");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(demo);
        }
        end.wait();
        System.out.println("Fire");
        executorService.shutdown();
    }
}
