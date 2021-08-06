package com.Java.cy;

/**
 * Author：CY
 * 试题：假设用户账号为：admin，密码为 123，编写用户登陆案例。 要求：请将登陆定义为 login 方法， 并将 login 方法写在 UserService 类中
 * Description：<描述>
 */
public class UserService {
    public Boolean login(String userName, String password) {
        if ("admin".equals(userName) && "123".equals(password)) {
            return true;
        }
        return false;
    }
}
