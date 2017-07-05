package com.tw.harper.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * Created by hbowang on 6/1/17.
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        //test Executor
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.execute(new MyExecutor(i));
        }
        System.out.println("submit finish....");
        executorService.shutdown();
        //test semaphore
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        Semaphore position = new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            Future<?> feature = executorService.submit(new MySemphore(i + 1, position));
        }
        executorService.shutdown();
        position.acquireUninterruptibly(2);
        System.out.println("The toilet used done,need to be clean");
        position.release();
    }

}
class MyExecutor extends Thread{
    private int index;
    public MyExecutor(int index){
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("["+this.index+"] start...");
        try {
            Thread.sleep((int)(Math.random()*10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("["+this.index+"] end...");
    }
}
class MySemphore extends Thread{
    Semaphore position;
    private int id;
    public MySemphore(int id, Semaphore position){
        this.id = id;
        this.position = position;
    }

    @Override
    public void run() {
        if(position.availablePermits() > 0){
            System.out.println("customer["+this.id+"] come into toilet.Free");
        }else{
            System.out.println("customer["+this.id+"] come into toilet.No Free.Wait!");
        }
        try {
            position.acquire();
            System.out.println("customer["+this.id+"] get the free count");
            Thread.sleep((int)(Math.random()*10000));
            System.out.println("customer["+this.id+"] had used the free count");
            position.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.run();
    }
}