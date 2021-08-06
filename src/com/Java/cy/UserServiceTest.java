package com.Java.cy;

import java.util.Scanner;

/**
 * Author：CY
 * Description：<描述>
 */
public class UserServiceTest {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = input.next();
        System.out.println("请输入密码：");
        String password = input.next();
        boolean result = userService.login(userName, password);
        if (result) {
            System.out.println("登录成功！");
        }else {
            System.out.println("登陆失败！用户名或密码错误！");
        }
    }
}
