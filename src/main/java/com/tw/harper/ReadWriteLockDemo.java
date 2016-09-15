package com.tw.harper;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hbowang on 5/14/16.
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;
    public Object handleRead(Lock lock) throws InterruptedException{
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("handleRead "+value);
            return value;
        }finally {
            lock.unlock();
        }
    }
    public void handleWriteLock(Lock lock,int index) throws InterruptedException{
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("handleWriteLock "+value);
            value = index;
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args){
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try{
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try{
                    demo.handleWriteLock(writeLock,new Random().nextInt());
//                    demo.handleWriteLock(lock,new Random().nextInt());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 18 ; i++){
            new Thread(readRunnable).start();
        }
        for(int i = 0; i < 18 ; i++){
            new Thread(writeRunnable).start();
        }
    }
}
