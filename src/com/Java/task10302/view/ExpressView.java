package com.Java.task10302.view;

import com.Java.task10302.entity.Express;
import com.Java.task10302.excption.OutNumberBoundExcption;

import java.util.Scanner;

/**
 * Author：CY
 * 视图层
 * Description：<描述>
 */
public class ExpressView {

    Scanner input = new Scanner(System.in);

    public int num = -1;
    public String text;

    /**
     * 欢迎
     */
    public void welcome() {
        System.out.println("欢迎使用快递管理系统！");
    }

    /**
     * 再见
     */
    public void bye() {
        System.out.println("欢迎下次使用！");
    }

    /**
     * 选择身份的菜单
     * @return
     */
    public int startMenu() {
        do {
            System.out.println("1.管理员");
            System.out.println("2.普通用户");
            System.out.println("0.退出");
            System.out.print("请输入功能序号：");
            //相对于nextInt，在这个方法内的逻辑，没有优点
            //考虑全局，所有方法均可使用nextLine，不会因输入产生冲突，也可以更好地接收各种类型的数据。
            text = input.nextLine();
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
            num = Integer.valueOf(textNum);
            if (num < begin || num > end) {
                throw new OutNumberBoundExcption("输入的序号范围必须在" + begin + "和" + end + "之间！");
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
            System.out.println("1.快递录入");
            System.out.println("2.快递删除");
            System.out.println("3.快递修改");
            System.out.println("4.查看所有快递");
            System.out.println("0.返回上层目录");
            System.out.print("请输入功能序号：");
            text = input.nextLine();
            try {
                num = validateNum(text, 0, 4);
                break;
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        } while (true);
        switch (num) {
            case 1:
                System.out.println("———————————————快递录入————————————————");
                break;
            case 2:
                System.out.println("———————————————快递删除————————————————");
                break;
            case 3:
                System.out.println("———————————————快递修改————————————————");
                break;
            case 4:
                System.out.println("—————————————查看所有快递——————————————");
                break;
            case 0:
                System.out.println("—————————————返回上层目录——————————————");
                break;
        }
        return num;
    }

    /**
     * 用户菜单
     * @return
     */
    public int userMenu() {
        do {
            System.out.println("1.快递取出");
            System.out.println("0.返回上层目录");
            System.out.print("请输入功能序号：");
            text = input.nextLine();
            try {
                num = validateNum(text, 0, 1);
                break;
            } catch (OutNumberBoundExcption e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        } while (true);
        switch (num) {
            case 1:
                System.out.println("———————————————快递取出————————————————");
                break;
            case 0:
                System.out.println("—————————————返回上层目录——————————————");
                break;
        }
        return num;
    }

    /**
     * 输入取件码
     * @return
     */
    public int findByCode() {
        do {
            System.out.println("请输入取件码：");
            String code = input.nextLine();
            num = -1;
            try {
                num = Integer.parseInt(code);
                if (num<100000 || num>999999){
                    System.out.println("输入有误，请重新输入：");
                }
                break;
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        } while (true);
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
        text = input.nextLine();
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
     * 输入快递单号
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
        System.out.println("快递公司：" + express.getCompany() + "，快递单号：" + express.getNumber() + "，取件码：" + express.getCode() + "，第" + express.getLocateNum() + "号柜子");
    }

    public void expressExist() {
        System.out.println("此单号在快递柜中已存在，请勿重复存储！");
    }

    public void printNull() {
        System.out.println("快递不存在，请检查输入：");
    }

    public void success(){
        System.out.println("操作成功！");
    }

    public void fail(){
        System.out.println("操作失败！");
    }


}
