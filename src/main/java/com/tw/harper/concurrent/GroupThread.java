package com.tw.harper.concurrent;

/**
 * Created by hbowang on 10/30/16.
 */
public class GroupThread implements Runnable{
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg,new GroupThread(),"t1");
        Thread t2 = new Thread(tg,new GroupThread(),"t2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }
    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
        while (true){
            System.out.println("I am "+groupAndName);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
