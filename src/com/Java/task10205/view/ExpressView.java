package com.Java.task10205.view;

import com.Java.task10205.dao.ExpressDao;
import com.Java.task10205.entity.Express;
import com.Java.task10205.excption.OutNumberBoundExcption;

import java.util.Scanner;

/**
 * Author：CY
 * 视图层
 * Description：<描述>
 */
public class ExpressView {
    private int num = -1;
    private Scanner input = new Scanner(System.in);
    private ExpressDao expressDao = new ExpressDao();

    /**
     * 欢迎
     */
    public void welcome() {
        System.out.println("欢迎使用XXX快递管理系统");
    }

    /**
     * 再见
     */
    public void bye() {
        System.out.println("欢迎下次使用");
    }

    /**
     * 选择身份的菜单
     * @return
     */
    public int startMenu() {
        do {
            System.out.println("请根据提示");
            System.out.println("1、管理员");
            System.out.println("2、普通用户");
            System.out.println("0、退出");
            System.out.print("输入功能序号：");
            //相对于nextInt，在这个方法内的逻辑，没有优点
            //考虑全局，所有方法均可使用nextLine，不会因输入产生冲突，也可以更好地接收各种类型的数据。
            String text = input.nextLine();
            try {
                num = validateNum(text, 0, 2);
                break;
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                //e.printStackTrace();
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
                //e.printStackTrace();
            }
        } while (true);
        return num;
    }

    /**
     * 验证用户输入是否合法
     * @param textNum
     * @param begin
     * @param end
     * @return
     * @throws NumberFormatException
     * @throws OutNumberBoundExcption
     */
    private int validateNum(String textNum, int begin, int end) throws NumberFormatException, OutNumberBoundExcption {
        try {
            int num = Integer.valueOf(textNum);
            if (num < begin || num > end) {
                throw new OutNumberBoundExcption("输入的序号范围必须在" + begin + "和" + end + "之间");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("请输入数字序号!");
        }
    }

    /**
     * 管理员菜单
     * @return
     */
    public int adminMenu() {
        do {
            System.out.println("请根据提示");
            System.out.println("1、快递录入");
            System.out.println("2、快递删除");
            System.out.println("3、快递修改");
            System.out.println("4、查看所有快递");
            System.out.println("0、返回上层目录");
            System.out.print("输入功能序号：");
            String text = input.nextLine();
            try {
                num = validateNum(text, 0, 4);
                break;
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        } while (true);
        if (num == 1) {
            System.out.println("———————————————快递录入————————————————");
        } else if (num == 2) {
            System.out.println("———————————————快递删除————————————————");
        } else if (num == 3) {
            System.out.println("———————————————快递修改————————————————");
        } else if (num == 4) {
            System.out.println("—————————————查看所有快递——————————————");
        } else if (num == 0) {
            System.out.println("—————————————返回上层目录——————————————");
        }
        return num;
    }

    /**
     * 用户菜单
     * @return
     */
    public int userMenu() {
        System.out.println("请根据提示，进行取件：");
        System.out.println("请输入您的取件码：");
        String code = input.nextLine();
        int num = -1;
        try {
            num = Integer.parseInt(code);
        } catch (NumberFormatException e) {

        } if (num<100000 || num>999999){
            System.out.println("输入有误，请重新输入：");
            return userMenu();
        }
        return num;
    }

    /**
     * 快递员录入快递
     * @return
     */
    public Express add() {
        System.out.println("请根据提示，输入快递信息：");
        System.out.println("请输入快递单号：");
        String number = input.nextLine();
        System.out.println("请输入快递公司：");
        String company = input.nextLine();
        Express express = new Express();
        express.setNumber(number);
        express.setCompany(company);
        return express;
    }

    /**
     * 询问是否确认删除
     * @return
     */
    public int delete() {
        System.out.println("是否确认删除？");
        System.out.println("1.确认删除");
        System.out.println("2.取消操作");
        String text = input.nextLine();
        int num = -1;
        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException e) {

        } if (num<0 || num>2){
            return delete();
        }
        return num;
    }

    /**
     * 修改快递信息
     * @param express
     */
    public void update(Express express) {
        System.out.println("请输入新的快递信息：");
        String number = input.nextLine();
        System.out.println("请输入新的快递公司：");
        String company = input.nextLine();
        express.setNumber(number);
        express.setCompany(company);
    }

    /**
     * 提示用户数快递单号
     * @return
     */
    public String findByNumber() {
        System.out.println("请根据提示，输入快递信息：");
        System.out.println("请输入要操作的快递单号：");
        String number = input.nextLine();
        return number;
    }


    /**
     * 显示快递信息
     * @param express
     */
    public void printExpress(Express express) {
        System.out.println("快递信息如下：");
        System.out.println("快递公司：" + express.getCompany() + "，快递单号：" + express.getNumber() + "，取件码" + express.getCode());
    }

    /**
     * 快递数组遍历显示
     * @param es
     */
    public void printAll(Express[][] es) {
        int count = 0;
        for (int i = 0; i < es.length; i++) {
            for (int j = 0; j < es[i].length; j++) {
                 if (es[i][j] != null) {
                     count++;
                     System.out.println("第" + (i+1) + "排 " + (j+1) + "列快递：");
                     printExpress(es[i][j]);
                 }
            }
        }
        if (count == 0) {
            System.out.println("暂无快递信息");
        }
    }

    public void expressExist() {
        System.out.println("此单号在快递柜中已存在，请勿重复存储");
    }

    public void pintNull() {
        System.out.println("快递不存在，请检查输入");
    }

    public void success(){
        System.out.println("操作成功");
    }

    public void fail(){
        System.out.println("操作失败");
    }

}
