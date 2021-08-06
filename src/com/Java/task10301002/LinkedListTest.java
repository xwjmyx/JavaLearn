package com.Java.task10301002;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Author：CY
 * 已知数组存放一批 QQ 号码,长度 5-11 位，
 * String[] strs = {"10001","10086","12347806666","45612378901","10001","12347806666"}。
 * 将该数组里面的所有 qq 号都存放在 LinkedList 中，将 list 中重复元素删除，将 list 中所有元素分别用迭代器和增强 for 循环打印出来。
 * Description：<描述>
 */
public class LinkedListTest {
    public static void main(String[] args) {
        String[] strs = {"10001","10086","12347806666","45612378901","10001","12347806666"};
        List<String> list = getList(strs);
        Iterator<String> iterator = list.iterator();

        //迭代器
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("——————————————————————");

        //增强for循环
        for (String s : list) {
            System.out.println(s);
        }

    }

    //获取不重复的list
    public static List getList(String[] strs) {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < strs.length; i++) {
            if (!list.contains(strs[i])) {//不包含则放入集合中
                list.add(strs[i]);
            }
        }
        return list;
    }
}
