package com.Java.library.entity;

import java.util.Date;

/**
 * Author：CY
 * Description：<描述>
 */
public class Book {

    private String name;
    private double price;
    private Date publicDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Book() {
    }

    public Book(String name, double price, Date publicDate) {
        this.name = name;
        this.price = price;
        this.publicDate = publicDate;
    }

    @Override
    public String toString() {
        return "book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", publicDate=" + publicDate +
                '}';
    }
}
