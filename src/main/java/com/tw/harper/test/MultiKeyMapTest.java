package com.tw.harper.test;

import com.google.common.base.Stopwatch;
import com.koloboke.collect.map.hash.HashObjObjMap;
import com.koloboke.collect.map.hash.HashObjObjMaps;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MultiKeyMapTest {
    public static void main(String[] args) {
        Stopwatch start = Stopwatch.createStarted();
        MultiKeyMap<String,Integer> map = new MultiKeyMap();
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i),String.valueOf(i),i);
        }
        System.out.println("MultiKeyMap put spend time:"+start);
        start = Stopwatch.createStarted();
        map.removeAll(String.valueOf(1));
        System.out.println("MultiKeyMap removeall spend time:"+start);

        start = Stopwatch.createStarted();
        Map<String,Integer> map1 = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map1.put(i+"_"+ i,i);
        }
        System.out.println("hashmap put spend time:"+start);

        start = Stopwatch.createStarted();
        Iterator<Map.Entry<String,Integer>> iter = map1.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String,Integer> entry = iter.next();
            if (entry.getKey().startsWith("1")) {
                iter.remove();
            }
        }
        System.out.println("hashmap remove spend time:"+start);
        System.out.println("-------------");
        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String b = "hello"+"_"+"world";
        }
        System.out.println("string + spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bbb = "hello".concat("_").concat("world");
        }
        System.out.println("string concat spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bb = String.join("_", "hello", "world");
        }
        System.out.println("string join spend time:"+start);
        System.out.println("-------------");

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String b1 = "hello11111111" + "world11111111";
        }
        System.out.println("string + spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String b1c = "hello11111111".concat("world11111111");
        }
        System.out.println("string concat spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bb1 = String.join("", "hello11111111", "world11111111");
        }
        System.out.println("string join spend time:"+start);
        System.out.println("=====================");



        System.out.println("-------------");
        start = Stopwatch.createStarted();
        String hello = "hello";
        String world = "world";
        String sperator = "_";
        String other = "other";
        for (int i = 0; i < 1000; i++) {
            String b = hello + sperator +world + other;
        }
        System.out.println("string parameter use + spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bbb = hello.concat(sperator).concat(world).concat(other);
        }
        System.out.println("string parameter use connect spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bbb = new StringBuffer().append(hello).append(sperator).append(world).append(other).toString();
        }
        System.out.println("string parameter use stringbuffer spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bb = String.join(sperator, hello, world,other);
        }
        System.out.println("string parameter  use join spend time:"+start);
        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bb = String.join(sperator, hello, world,other);
        }
        System.out.println("three string parameter  use join spend time:"+start);

        start = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            String bb = hello + sperator + world;
        }
        System.out.println("three string parameter use + spend time:"+start);

        System.out.println("-------------");


        start = Stopwatch.createStarted();
        MultiKeyMap<String,Integer> map2 = new MultiKeyMap();
        for (int i = 0; i < 100; i++) {
            map2.put(String.valueOf(i),String.valueOf(i),i);
        }
        System.out.println("MultiKeyMap put spend time:"+start);
        start = Stopwatch.createStarted();
        map2.removeAll(String.valueOf(1));
        System.out.println("MultiKeyMap removeall spend time:"+start);

        start = Stopwatch.createStarted();
        Map<String,Integer> map3 = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map3.put(i+"_"+ i,i);
        }
        System.out.println("hashmap put spend time:"+start);








    }
}
