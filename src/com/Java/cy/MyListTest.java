package com.Java.cy;

/**
 * Author：CY
 * Description：<描述>
 */
public class MyListTest {
    public static void main(String[] args) {
        MyList list = new MyList();
        //添加
        for (int i = 0; i < 5; i++) {
             list.add("abc" + i);
        }
        //删除
        Object obj1 = list.remove(2);
        System.out.println("删除的元素：" + obj1);
        String s = "test";
        list.add(s);
        Object obj2 = list.remove(s);
        System.out.println("删除的元素：" + obj2);
        //获取
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }


    }
}
