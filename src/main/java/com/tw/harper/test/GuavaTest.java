package com.tw.harper.test;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import com.tw.harper.model.Book;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GuavaTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Set<Book> bookSet = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            bookSet.add(new Book("java"+i,"allen"+1,10d) );
        }
        Stopwatch start = Stopwatch.createStarted();
        new HashSet<Book>(bookSet);
        System.out.println("use connect spend time:"+start);

        start = Stopwatch.createStarted();
        Sets.newHashSet(bookSet);
        System.out.println("use connect spend time:"+start);

        Set<String> bookNames = null;
        start = Stopwatch.createStarted();
        bookNames = bookSet.stream().map(book -> book.getName()).collect(Collectors.toSet());
        System.out.println("use connect spend time:"+start);

        start = Stopwatch.createStarted();
        bookNames = new HashSet<>();
        for (Book book : bookSet){
            bookNames.add(book.getName());
        }
        System.out.println("use connect spend time:"+start);

    }
}
