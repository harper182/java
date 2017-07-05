package com.tw.harper.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by hbowang on 11/6/16.
 */
public class MyForkJoinTask extends RecursiveTask<Long>{
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;
    public MyForkJoinTask(long start, long end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if(canCompute){
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        }else{
            //分成100个小任务
            long step = (start+end)/100;
            ArrayList<MyForkJoinTask> subTasks = new ArrayList<>();
            long pos = start;
            for (int index = 0; index < 100; index++) {
                long lastOne = pos+step;
                if(lastOne > end) lastOne = end;
                MyForkJoinTask subTask = new MyForkJoinTask(pos,lastOne);
                pos += step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for(MyForkJoinTask task : subTasks){
                sum += task.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        ForkJoinPool forkJoinPool= new ForkJoinPool();
//        MyForkJoinTask task = new MyForkJoinTask(0,200000L);
//        MyForkJoinTask result = forkJoinPool.submit(task);
//        try {
//            Long res = result.get();
//            System.out.println("sum="+res);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }catch (ExecutionException e){
//            e.printStackTrace();
//        }
//        forkJoinPool.shutdown();
    }
}
