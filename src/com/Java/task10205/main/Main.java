package com.Java.task10205.main;

import com.Java.task10205.entity.Express;
import com.Java.task10205.dao.ExpressDao;
import com.Java.task10205.view.ExpressView;

/**
 * Author：CY
 * DATE：main
 * Description：<描述>
 */
public class Main {
    //初始化视图对象
    public static ExpressView view = new ExpressView();
    //初始化dao对象
    public static ExpressDao expressDao = new ExpressDao();
    public static void main(String[] args) {
        //1.欢迎
        view.welcome();
        while (true) {
            //2.弹出身份选择菜单
            int menu = view.startMenu();
            switch (menu) {
                case 0:
                    System.out.println("—————————————成功退出系统——————————————");
                    view.bye();
                    return;
                case 1:
                    System.out.println("——————————————管理员菜单——————————————");
                    System.out.println("管理员1");
                    admin();
                    break;
                case 2:
                    System.out.println("—————————————普通用户菜单——————————————");
                    System.out.println("用户2");
                    user();
                    break;
            }
        }

    }

    private static void user() {
        //1.获取取件码
        int code = view.userMenu();
        //2.获取取件码，取出快递
        Express express = expressDao.findByCode(code);
        if (express == null) {
            view.pintNull();
        } else {
            try {
                expressDao.delete(express);
                view.success();
                view.printExpress(express);
            } catch (Exception e) {
                view.fail();
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void admin() {
        int menu = view.adminMenu();
        switch (menu) {
            case 0:
                return;
            case 1: {
                //1.提示输入快递信息
                Express express = view.add();
                //2.此快递是否已经存储过
                Express express2 = expressDao.findByNumber(express.getNumber());
                //3.存储快递
                if (express2 == null) {
                    //未存储过
                    try {
                        expressDao.add(express);
                        view.success();
                    } catch (Exception e) {
                        view.fail();
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                    view.printExpress(express);
                } else {
                    //单号在快递柜中已存在
                    view.expressExist();
                }
            }
            break;
            case 2: {
                //删除
                //1.输入快递单号
                String number = view.findByNumber();
                //2.查找快递对象
                Express express = expressDao.findByNumber(number);
                if (express == null) {
                    view.pintNull();
                } else {
                    view.printExpress(express);
                    int type = view.delete();
                    if (type == 1) {
                        try {
                            expressDao.delete(express);
                            view.success();
                        } catch (Exception e) {
                            view.fail();
                            System.err.println(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
            break;
            case 3: {
                //1.提示输入快递信息
                String number = view.findByNumber();
                //2.查找数据
                Express express = expressDao.findByNumber(number);
                Express express2 = express;
                //3.打印快递信息
                if (express == null) {
                    view.pintNull();
                } else {
                    view.printExpress(express);
                    //4.提示修改
                    view.update(express2);
                    try {
                        expressDao.update(express, express2);
                        view.success();
                    } catch (Exception e) {
                        view.fail();
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            break;
            case 4: {
                //查看所有
                Express[][] expressArr = expressDao.findAll();
                view.printAll(expressArr);
            }
            break;
        }
    }
}
