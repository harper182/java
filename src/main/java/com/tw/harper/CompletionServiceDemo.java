package com.tw.harper;

import java.util.concurrent.*;

/**
 * Created by hbowang on 6/2/17.
 */
public class CompletionServiceDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new MyCompletion(i));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(completionService.take().get());
        }
        executorService.shutdown();
    }
}
class MyCompletion implements Callable<String> {
    private int id;
    public MyCompletion(int id){
        this.id =id;
    }
    @Override
    public String call() throws Exception {
        Integer time = (int)(Math.random()*1000);
        System.out.println(this.id+" start...");
        Thread.sleep(time);
        System.out.println(this.id+"  end...");

        return this.id+":"+time;
    }
}
