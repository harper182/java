package com.tw.harper;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hbowang on 5/14/16.
 */
public class ThreadPoolDemo {
    public static class MyTest implements Runnable{
        @Override
        public void run(){
            System.out.println(System.currentTimeMillis() + ":Thread id:"+Thread.currentThread().getId());
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTest test = new MyTest();
        Date date = new Date(1464242760000L);
        System.out.println(date);
//        ExecutorService es = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            es.submit(test);
//        }
    }
}
