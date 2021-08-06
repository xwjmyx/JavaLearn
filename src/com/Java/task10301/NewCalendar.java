package com.Java.task10301;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Author：CY
 * Description：<描述>
 */
public class NewCalendar {
    public static void main(String[] args) throws ParseException {
        Calendar cl = Calendar.getInstance();
        System.out.println("请输入年：");
        int year = inputNum(1);
        System.out.println("请输入月：");
        int month = inputNum(2);
        //设置日历年
        cl.set(Calendar.YEAR, year);
        //设置日历月
        cl.set(Calendar.MONTH, month-1);
        //设置当月第一天
        cl.set(Calendar.DATE, 1);

        //当前月天数
        int currentMonthDay = cl.getActualMaximum(Calendar.DAY_OF_MONTH);
        //当前月第一天星期几
        int weekDay = cl.get(Calendar.DAY_OF_WEEK) - 1;

        System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
        for (int i = 0; i < weekDay; i++) {
            System.out.printf("%-8s","");
        }

        int relaxDays = 0;
        int relaxWeekends = 0;

        for (int i = 1; i <= currentMonthDay; i++) {
            //当月第一天
            cl.set(Calendar.DATE, i);
            //距离第一天休息间隔天数
            int calculateDays = calculateDays(cl);
            if (calculateDays%4 == 0) {
                System.out.printf("%-8s","[" + i + "]");
                relaxDays++;
                if (cl.get(Calendar.DAY_OF_WEEK) == 1 || cl.get(Calendar.DAY_OF_WEEK) == 7){
                    relaxWeekends++;
                }
            } else {
                System.out.printf("%-8s", i);
            }

            if (cl.get(Calendar.DAY_OF_WEEK) == 7) {
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("本月休息天数有：" + relaxDays + "天");
        System.out.println("本月轮到周末休息天数是：" + relaxWeekends + "天");
    }


    public static int inputNum(int cho) {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        int num = 0;
        switch (cho){
            case 1 :
                try {
                    num = Integer.valueOf(text);
                    break;
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    System.out.println("输入有误，请重新输入：");
                    inputNum(1);
                }
                break;
            case 2:
                try {
                    num = validateNum(text, 1,12);
                    break;
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    System.out.println("输入有误，请重新输入：");
                    inputNum(2);
                } catch (OutNumberBoundExcption e) {
                    System.err.println(e.getMessage());
                    System.out.println("输入有误，请重新输入：");
                    inputNum(2);
                }
                break;
        }
        return num;
    }

    private static int validateNum(String textNum, int begin, int end) throws NumberFormatException, OutNumberBoundExcption {
        try {
            int num = Integer.valueOf(textNum);
            if (num < begin || num > end) {
                throw new OutNumberBoundExcption("输入的月范围必须在" + begin + "和" + end + "之间");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("请输入数字月份!");
        }
    }


    public static int calculateDays(Calendar cl) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2019-2-2");
        long start = date.getTime();
        long end = cl.getTime().getTime();
        int cDays;
        if (end >= start) {
            cDays = (int) ((end - start)/(1000*60*60*24));
        } else {
            return -1;
        }
        return cDays;
    }

    public static class OutNumberBoundExcption extends Throwable {
        public OutNumberBoundExcption(String s) {
            super(s);
        }
    }
}
