package com.Java.library.view;

import com.Java.library.dao.BookDao;
import com.Java.library.entity.Book;
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
    Book book = new Book();
    BookDao bookDao = new BookDao();
    String text;
    int num = -1;

    public void bye() {
        System.out.println("欢迎下次继续使用。");
        System.exit(0);
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

    //用户是否登录成功
    public boolean ifLoginIn() {
        for (int i = 3; i > 0;) {
            System.out.println("请输入用户名：");
            String userName = input.nextLine();
            System.out.println("请输入密码：");
            String passWord = input.nextLine();
            if (userName.equals("admin") && passWord.equals("123456")) {
                System.out.println("欢迎登录，" + userName);
                return true;
            } else if (--i != 0) {
                System.err.println("用户名或密码错误，请重新输入！");
                System.err.println("还有" + i + "次机会。");
            }
        }
        return false;
    }

    //主菜单
    public int adminMenu() {
        while (true) {
            System.out.println("请选择操作序号：");
            System.out.println("1、新增图书");
            System.out.println("2、删除图书");
            System.out.println("3、修改图书");
            System.out.println("4、根据图书名模糊查找");
            System.out.println("5、查看所有图书");
            System.out.println("0、退出");
            this.text = input.nextLine();
            int begin = 0;
            int end = 5;
            try {
                num = validate(this.text, begin, end);
                break;
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            }
        }
        return num;
    }

    //排序菜单
    public int sortMenu() {
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
                break;
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            }
        }
        return num;
    }

    //新增图书
    public Book add() {
        double price = 0;
        Date date;
        do {
            System.out.println("请输入图书名字：");
            text = input.nextLine();
            System.out.println("请输入价格：");
            String priceText = input.nextLine();
            try {
                price = validate(priceText, 0);
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
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
        if (bookDao.findByName(text) == null) {
            book = new Book();
            book.setName(text);
            book.setPrice(price);
            book.setPublicDate(date);
            return book;
        } else {
            error(1);
        }
        return null;
    }

    //删除图书
    public String delete() {
        if (ifIsExist()) {
            System.out.println("请输入图书名字：");
            text = input.nextLine();
            while (bookDao.findByName(text) == null) {
                System.err.println("未找到名为" + text + "的书，请重新输入：");
                text = input.nextLine();
            }
            return text;
        }
        return null;
    }

    public String update() {
        if (ifIsExist()) {
            System.out.println("请输入图书名字：");
            text = input.nextLine();
            while (bookDao.findByName(text) == null) {
                System.err.println("未找到名为" + text + "的书，请重新输入：");
                text = input.nextLine();
            }
            return text;
        }
        return null;
    }

    //根据名字模糊查找
    public String fuzzyFindByName() {
        if (ifIsExist()) {
            System.out.println("请输入需要查找的内容：");
            this.text = input.nextLine();
        }
        return this.text;
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