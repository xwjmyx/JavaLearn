package com.Java.library.dao;

import com.Java.library.entity.Book;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：CY
 * Description：<描述>
 */
public class BookDao {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<Book> bookList = new ArrayList<>(100);
    Book book = new Book();
    public List<Book> getBookList() {
        return bookList;
    }

    //添加
    public void add(String name, double price, Date date) {
        book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setPublicDate(date);
        bookList.add(book);
    }

    //删除
    public void del(String name) {
        for (int i = 0; i < bookList.size(); i++) {
             if (bookList.get(i).getName().equals(name)) {
                 bookList.remove(i);
                 break;
             }
        }
    }

    //根据名字查找
    public Book findByName(String name) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getName().equals(name)) {
                return bookList.get(i);
            }
        }
        return null;
    }

    //匹配名字
    public void matchName(String text) {
        boolean find = false;
        Pattern pattern = Pattern.compile(text);
        Iterator<Book> bookIterator = bookList.iterator();
        while (bookIterator.hasNext()) {
            book = bookIterator.next();
            Matcher matcher =pattern.matcher(book.getName());
            if (matcher.find()) {
                System.out.println("书名：" + book.getName()+ "，价格：" + book.getPrice() + "，出版日期：" + dateFormat.format(book.getPublicDate()));
                find = true;
            }
        }
        if (!find) {
            System.err.println("未找到书名含有" + "\"" + text + "\"" + "的图书。");
        }
    }

    //打印所有
    public void printAll() {
        for (Book book : bookList) {
            System.out.println("书名：" + book.getName()+ "，价格：" + book.getPrice() + "，出版日期：" + dateFormat.format(book.getPublicDate()));
        }
    }

    public void compareByPriceAsc() {
        Collections.sort(bookList, new CompareByPriceAsc());
        printAll();
    }

    //按照格升序排列
    public static class CompareByPriceAsc implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1.getPrice() > o2.getPrice()) {
                return 1;
            } else if (o1.getPrice() == o2.getPrice()) {
                if (o1.getPublicDate().before(o2.getPublicDate()))
                    return -1;
                else
                    return 1;
            } else
                return -1;
        }
    }

    public void compareByPriceDesc() {
        Collections.sort(bookList, new CompareByPriceDesc());
        printAll();
    }

    //按照书籍价格降序排列
    public static class CompareByPriceDesc implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1.getPrice() > o2.getPrice()) {
                return -1;
            } else if (o1.getPrice() == o2.getPrice()) {
                if (o1.getPublicDate().before(o2.getPublicDate()))
                    return 1;
                else
                    return -1;
            } else
                return 1;
        }
    }

    public void compareByDateDesc() {
        Collections.sort(bookList, new CompareByDateDesc());
        printAll();
    }

    //按照出版时间降序排列
    public static class CompareByDateDesc implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1.getPublicDate().before(o2.getPublicDate())) {
                return 1;
            } else if (o1.getPublicDate().equals(o2.getPublicDate())) {
                if (o1.getPrice() > o2.getPrice())
                    return -1;
                else
                    return 1;
            } else
                return -1;
        }
    }
}
