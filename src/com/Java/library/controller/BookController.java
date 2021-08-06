package com.Java.library.controller;

import com.Java.library.view.BookView;

import java.util.Scanner;

/**
 * Author：CY
 * DATE：2021-08-2021/8/6 14:39
 * Description：<描述>
 */
public class BookController {
    Scanner input = new Scanner(System.in);
    BookView bookView = new BookView();

    public void login() {
        ifLoginIn();
    }

    //用户是否登录成功
    public void ifLoginIn() {
        bookView.welcome();
        for (int i = 3; i > 0;) {
            System.out.println("请输入用户名：");
            String userName = input.nextLine();
            System.out.println("请输入密码：");
            String passWord = input.nextLine();
            if (userName.equals("admin") && passWord.equals("123456")) {
                System.out.println("欢迎登录，" + userName);
                bookView.menu();
            } else if (--i == 0) {
                bookView.bye();
            } else {
                System.err.println("用户名或密码错误，请重新输入！");
                System.err.println("还有" + i + "次机会。");
            }
        }
    }
}
