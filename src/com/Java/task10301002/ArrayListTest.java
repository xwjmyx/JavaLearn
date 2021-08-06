package com.Java.task10301002;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：CY
 * 定义一个方法 listTest(ArrayList<String> list, String name)，要求返回name 在 list 里面第一次出现的索引，如果 name 没出现过返回-1
 * Description：<描述>
 */
public class ArrayListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        for (String s : list) {
            System.out.println(s);
        }
        int result;
        result = findName(list, "赵六");
        System.out.println("赵六查询结果：" + result);
        result = findName(list, "周七");
        System.out.println("周七查询结果：" + result);
    }

    public static int findName(List list, String name) {
        for (int i = 0; i < list.size(); i++) {
             if (list.get(i) == name) {
                return i;
             }
        }
        return -1;
    }
}
