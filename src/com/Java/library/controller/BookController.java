package com.Java.library.controller;

import com.Java.library.dao.BookDao;
import com.Java.library.entity.Book;
import com.Java.library.view.BookView;

/**
 * Author：CY
 * Description：<描述>
 */
public class BookController {

    BookView bookView = new BookView();
    BookDao bookDao = new BookDao();
    String text;
    int num = -1;

    public void welcome() {
        System.out.println("欢迎使用图书管理系统！");
    }

    public void login() {
        welcome();
        while (bookView.ifLoginIn()) {
            adminOperate();
            //bookView.adminMenu();
            break;
        }
        bookView.bye();

    }

    //管理员操作
    private void adminOperate() {
        while (true) {
            num = bookView.adminMenu();
            switch (num) {
                case 0: {
                    return;
                }
                case 1: {
                    try {
                        addBook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    try {
                        delBook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    updateBook();
                    break;
                }
                case 4: {
                    fuzzyFindByName();
                    break;
                }
                case 5: {
                    sortOperate();
                    break;
                }
            }
        }
    }

    //排序操作
    private void sortOperate() {
        num = bookView.sortMenu();
        switch (num) {
            case 0:
                return;
            case 1:
                printAll();
                break;
            case 2:
            case 3:
            case 4:
                sorting(num);
                break;
        }
    }


    //新增图书
    public void addBook() {
        Book book = bookView.add();
        bookDao.add(book);
    }

    //删除图书
    public void delBook() {
        text = bookView.delete();
        while (text != null) {
            bookDao.del(text);
        }
    }

    //修改图书
    public void updateBook() {
        while (bookView.update() != null) {
            delBook();
            addBook();
        }
    }

    //根据名字模糊查找
    public void fuzzyFindByName() {
        if (bookView.ifIsExist()) {
            text = bookView.fuzzyFindByName();
            bookDao.matchName(text);
        }
    }

    //打印所有图书
    public void printAll() {
        if (ifIsExist()) {
            bookDao.printAll();
        }
    }

    //排序打印所有图书
    private void sorting(int num) {
        if (ifIsExist()) {
            switch (num) {
                case 2:
                    bookDao.compareByPriceDesc();
                    break;
                case 3:
                    bookDao.compareByPriceAsc();
                    break;
                case 4:
                    bookDao.compareByDateDesc();
                    break;
            }
        }
    }

    public boolean ifIsExist() {
        if (bookDao.getBookList().size() > 0) {
            return true;
        } else {
            bookView.error(2);
            return false;
        }
    }
}
