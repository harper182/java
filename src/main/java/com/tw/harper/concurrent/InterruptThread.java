package com.tw.harper.concurrent;

/**
 * Created by hbowang on 10/30/16.
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interrupt");
                        break;
                    }
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        System.out.println("interrupt when sleep");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

}
