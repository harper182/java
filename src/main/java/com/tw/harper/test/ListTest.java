package com.tw.harper.test;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        int n = 1000;
        String[] a = new String[n];
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i] = "hello"+i;
            list.add(a[i]);
        }
        Stopwatch started = Stopwatch.createStarted();
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
        System.out.println("array fori spend time :"+started);
        started = Stopwatch.createStarted();
        for(String str : a){
            System.out.print(str);
        }
        System.out.println();
        System.out.println("array for array spend time :"+started);

        started = Stopwatch.createStarted();
        for (int i = 0; i < a.length; i++) {
            System.out.print(list.get(i));
        }
        System.out.println();
        System.out.println("list fori spend time :"+started);
        started = Stopwatch.createStarted();
        for(String str : list){
            System.out.print(str);
        }
        System.out.println();
        System.out.println("list for array spend time :"+started);


    }
}
