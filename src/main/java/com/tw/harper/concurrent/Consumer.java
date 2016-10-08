package com.tw.harper.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * Created by hbowang on 10/8/16.
 */
public class Consumer implements Runnable {
    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
