package com.tw.harper.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * Created by hbowang on 10/8/16.
 */
public class Producer implements Runnable {
    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("11");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("33");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
