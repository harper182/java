package com.tw.harper.model;

import java.io.Serializable;

/**
 * Created by hbowang on 5/31/16.
 */
public class Book implements Serializable,Cloneable{
    private String name;
    private String author;
    private double price;
    public Book(){

    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book)super.clone();
    }

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
