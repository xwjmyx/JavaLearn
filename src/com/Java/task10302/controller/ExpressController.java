package com.Java.task10302.controller;

import com.Java.task10302.dao.ExpressDao;
import com.Java.task10302.entity.Express;
import com.Java.task10302.view.ExpressView;

import java.util.Map;

/**
 * Author：CY
 * 控制层
 * Description：<描述>
 */
public class ExpressController {

    ExpressView expressView = new ExpressView();
    ExpressDao expressDao = new ExpressDao();
    int menu;

    public void login() {
        expressView.welcome();
        while (true) {
            //2.弹出身份选择菜单
            menu = expressView.startMenu();
            switch (menu) {
                case 0:
                    System.out.println("—————————————成功退出系统——————————————");
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
        //expressView.bye();
    }

    //管理员操作
    private void admin() {
        menu = expressView.adminMenu();
        switch (menu) {
            case 0://返回上一级
                return;
            case 1:
                addExpress();
                break;
            case 2:
                delExpress();
                break;
            case 3:
                updateExpress();
                break;
            case 4:
                findAll();
                break;
        }
    }

    //用户操作
    private void user() {
        menu = expressView.userMenu();
        switch (menu) {
            case 0://返回上一级
                return;
            case 1:
                getByCode();
                break;
        }
    }

    private void addExpress() {//添加
        //1.提示输入快递信息
        Express express1 = expressView.add();
        //2.此快递是否已经存储过
        Express express2 = expressDao.findByNumber(express1.getNumber());
        //3.存储快递
        if (express2 == null) {//未存储过
            try {
                expressDao.add(express1);
                expressView.success();
            } catch (Exception e) {
                expressView.fail();
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
            expressView.printExpress(express1);
        } else {
            //单号在快递柜中已存在
            expressView.expressExist();
        }
    }

    private void delExpress() {//删除
        //1.输入快递单号
        String number = expressView.findByNumber();
        //2.查找快递对象
        Express express = expressDao.findByNumber(number);
        if (express == null) {
            expressView.printNull();
        } else {
            expressView.printExpress(express);
            int type = expressView.delete();
            if (type == 1) {
                try {
                    expressDao.delete(number);
                    expressView.success();
                } catch (Exception e) {
                    expressView.fail();
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateExpress() {
        //1.提示输入快递信息
        String number = expressView.findByNumber();
        //2.查找数据
        Express express1 = expressDao.findByNumber(number);
        Express express2 = express1;
        //3.打印快递信息
        if (express1 == null) {
            expressView.printNull();
        } else {
            expressView.printExpress(express1);
            //4.提示修改
            expressView.update(express2);
            try {
                expressDao.update(number, express2);
                expressView.success();
            } catch (Exception e) {
                expressView.fail();
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void findAll() {
        //查看所有
        Map<String, Express> expressMap = expressDao.getExpressMap();
        for (Express express : expressMap.values()) {
            System.out.println(express);
        }
    }

    private void getByCode() {
        //1.获取取件码
        int code = expressView.findByCode();
        //2.获取取件码，取出快递
        Express express = expressDao.findByCode(code);
        if (express == null) {
            expressView.printNull();
        } else {
            try {
                expressDao.delete(express.getNumber());
                expressView.success();
                expressView.printExpress(express);
            } catch (Exception e) {
                expressView.fail();
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
