package com.tw.harper;

/**
 * Created by hbowang on 5/9/16.
 */
public class MultiThread implements Runnable {
    private static MyInteger myInteger = null;

    static {
        myInteger = new MyInteger();
    }

    @Override
    public void run() {
        for (int index = 0; index < 3; index++) {
            myInteger.add();
            System.out.println(Thread.currentThread().getName()+":i="+myInteger.getInteger());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int index = 0; index < 100; index++) {
            Thread tt = new Thread(new MultiThread());
            tt.start();
        }
        System.out.println("integer=------" + myInteger.getInteger());
    }
}

class MyInteger {
    private Integer integer = 1;

    public synchronized void add() {
        integer++;
    }

    public Integer getInteger() {
        return integer;
    }
}

class MyIntegerFactory {
    private static MyInteger myInteger;

    public MyInteger getInstance() {
        if (myInteger == null) {
            myInteger = new MyInteger();
        }
        return myInteger;
    }
}
