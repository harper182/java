package com.tw.harper;

/**
 * Created by hbowang on 6/2/16.
 */
public class MyThread {
    public static void main(String[] args) {
        for (int index = 0; index < 100; index++) {
            new Thread(new MyRunnable(index)).start();
        }
    }
}

class MyRunnable implements Runnable{
    private int number;

    public MyRunnable(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        Calculator.test(number);
    }
}
class Calculator {
    public static void test(int number){
        System.out.println(number+"*"+number+"="+number*number);
    }
}
