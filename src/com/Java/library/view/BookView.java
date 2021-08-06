package com.Java.library.view;

import com.Java.library.dao.BookDao;
import com.Java.library.excption.OutNumberBoundExcption;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author：CY
 * Description：<描述>
 */
public class BookView {

    Scanner input = new Scanner(System.in);
    BookDao bookDao = new BookDao();
    String name;
    String text;
    int num;

    public void welcome() {
        System.out.println("欢迎使用图书管理系统！");
    }

    public void bye() {
        System.out.println("欢迎下次继续使用。");
        System.exit(0);
    }

    public void success() {
        System.out.println("操作成功！");
    }

    public void error(int num) {
        switch (num) {
            case 1:
                System.err.println("已存在此书");
                break;
            case 2:
                System.err.println("系统中没有可供操作的图书，请先储存。");
                break;
        }
    }

    /*//用户是否登录成功
    public void ifLoginIn() {
        welcome();
        for (int i = 3; i > 0;) {
            System.out.println("请输入用户名：");
            String userName = input.nextLine();
            System.out.println("请输入密码：");
            String passWord = input.nextLine();
            if (userName.equals("admin") && passWord.equals("123456")) {
                System.out.println("欢迎登录，" + userName);
                menu();
            } else if (--i == 0) {
                bye();
            } else {
                System.err.println("用户名或密码错误，请重新输入！");
                System.err.println("还有" + i + "次机会。");
            }
        }
    }*/

    //主菜单
    public void menu() {
        while (true) {
            System.out.println("请选择操作序号：");
            System.out.println("1、新增图书");
            System.out.println("2、删除图书");
            System.out.println("3、修改图书");
            System.out.println("4、根据图书名模糊查找");
            System.out.println("5、查看所有图书");
            System.out.println("0、退出");
            text = input.nextLine();
            int begin = 0;
            int end = 5;
            try {
                num = validate(text, begin, end);
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            }
            switch (num) {
                case 0: {
                    bye();
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
                        DelBook();
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
                    sortMenu();
                    break;
                }
            }
        }
    }

    //排序菜单
    private void sortMenu() {
        while (true) {
            System.out.println("请选择排序方式：");
            System.out.println("1、不排序");
            System.out.println("2、价格从高到低排序");
            System.out.println("3、价格从低到高排序");
            System.out.println("4、新旧排序(出版日期排序)");
            System.out.println("0、退出");
            text = input.nextLine();
            int begin = 0;
            int end = 4;
            try {
                num = validate(text, begin, end);
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            }
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
    }

    //新增图书
    public void addBook() {
        double price = 0;
        Date date = null;
        do {
            System.out.println("请输入图书名字：");
            name = input.nextLine();
            System.out.println("请输入价格：");
            String priceText = input.nextLine();
            try {
                price = validate(priceText, 0);
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                continue;
            }
            System.out.println("请输入出版日期（格式：yyyy-MM-dd）：");
            String time = input.nextLine();
            try {
                date = validate(time);
                break;
            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }
        } while (true);
        if (bookDao.findByName(name) == null) {
            bookDao.add(name, price, date);
            success();
        } else {
            error(1);
        }
        return;
    }

    //删除图书
    public void DelBook() {
        if (ifIsExist()) {
            System.out.println("请输入图书名字：");
            name = input.nextLine();
            while (bookDao.findByName(name) == null) {
                System.err.println("未找到名为" + name + "的书，请重新输入：");
                name = input.nextLine();
            }
            bookDao.del(name);
            success();
            return;
        }
    }

    //修改图书
    public void updateBook() {
        if (ifIsExist()) {
            System.out.println("请输入图书名字：");
            name = input.nextLine();
            while (bookDao.findByName(name) == null) {
                System.err.println("未找到名为" + name + "的书，请重新输入：");
                name = input.nextLine();
            }
            bookDao.del(name);
            addBook();
        }
    }

    //根据名字模糊查找
    public void fuzzyFindByName() {
        if (ifIsExist()) {
            System.out.println("请输入需要查找的内容：");
            text = input.nextLine();
            bookDao.matchName(text);
            return;
        }
    }

    //打印所有图书
    public void printAll() {
        if (ifIsExist()) {
            bookDao.printAll();
            return;
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
            error(2);
            return false;
        }
    }

    //判断输入是否是有效序号
    private int validate(String numText, int begin, int end) throws OutNumberBoundExcption {
        try {
            num = Integer.parseInt(numText);
            if (num < begin || num > end)
                throw new OutNumberBoundExcption("输入的序号范围必须在" + begin + "和" + end + "之间");
            return num;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("请输入数字序号");
        }
    }

    //判断输入是否是有效价格
    private double validate(String priceText, int begin) throws OutNumberBoundExcption {
        try {
            double price = Double.parseDouble(priceText);
            if (price < 0)
                throw new OutNumberBoundExcption("价格不能小于"+ begin + "，请输入有效的价格");
            return price;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("请输入数字");
        }
    }

    //判断输入是否是有效日期格式
    private Date validate(String time) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(time);
            return date;
        } catch (ParseException e) {
            throw new ParseException("请输入符合要求的内容", -1);
        }
    }
}