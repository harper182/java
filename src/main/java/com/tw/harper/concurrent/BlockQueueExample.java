package com.tw.harper.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by hbowang on 10/8/16.
 */
public class BlockQueueExample {
    public static void testLinkedBlockingQueue() throws InterruptedException {
        //链阻塞队列LinkedBlockingQueue
        //LinkedBlockingQueue 类实现了 BlockingQueue 接口。
        //LinkedBlockingQueue 内部以一个链式结构(链接节点)对其元素进行存储。如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE 作为上限。
        //LinkedBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
        BlockingQueue queue = new LinkedBlockingDeque<>(1024);
        queue.put("hello");
        System.out.println(queue.take());
    }
    public static void main(String[] args) throws InterruptedException {
        //ArrayBlockingQueue 是一个有界的阻塞队列，其内部实现是将对象放到一个数组里。有界也就意味着，它不能够存储无限多数量的元素。它有一个同一时间能够存储元素数量的上限。你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了(译者注：因为它是基于数组实现的，也就具有数组的特性：一旦初始化，大小就无法修改)。
        BlockingQueue queue = new ArrayBlockingQueue(1024);
        //DelayQueue 对元素进行持有直到一个特定的延迟到期,
        //Delayed element1 = new DelayedElement();
//        queue.put(element1);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(3000);
        testLinkedBlockingQueue();
    }
}
