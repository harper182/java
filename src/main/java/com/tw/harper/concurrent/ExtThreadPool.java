package com.tw.harper.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hbowang on 11/2/16.
 */
public class ExtThreadPool {
    public static class MyTask implements Runnable{
        private String name;
        public MyTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println("正在执行:Thread id:"+Thread.currentThread().getId()+",Task name:"+name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行: "+((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成: "+((MyTask)r).name);
            }
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("TASK-"+i);
            executorService.execute(task);
            Thread.sleep(10);
        }
        executorService.shutdown();
    }
}
