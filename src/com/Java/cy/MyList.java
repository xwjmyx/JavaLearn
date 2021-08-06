package com.Java.cy;

/**
 * Author：CY
 * 自定义一个类， 命名为 MyList，类中包含属性：Object[] element;
 * 定义如下几个方法：
 * 1. 增加方法 add ： 可以向数组属性中依次存储 Object，数组内容存满时，需实现动态扩容（详解在下面）。 参考: Boolean add(Objectobj)
 * 2. 删除方法 remove ： 可以根据数据或下标，从数组属性中删除 Object 数据，删除后，数组后续 元素需前移。参考:void remove(Objectobj) 或 void remove(Integer index)
 * 3. 查询方法 get ： 方法传入下标，返回数组中指定下标的据。
 * 参考:Object get(Integer index)
 * 4. 当前存储数据量 size ： 获取当前存储的有效数据长度
 * 参考:size=数组.length
 *
 * Description：<描述>
 */
public class MyList {

    //属性
    private Object[] element;//盛放元素的数组
    private int capacity;//容量
    private int size;//实际大小

    public int getSize() {
        return size;
    }

    //构造方法
    public MyList() {
        size = 0;
        capacity = 4;//默认容量
        element = new Object[capacity];//分配数组的空间
    }

    //方法
    public void add(Object obj) {
        if (size >= capacity) {//数组满后需要扩容
            //动态扩容
            Object[] newArr = new Object[capacity * 2];
            //将原有数组的元素复制到新数组中
            for (int i = 0; i < element.length; i++) {
                newArr[i] = element[i];
            }
            //原数组名等于新数组名
            element = newArr;
        }
        element[size] = obj;//存放元素
        size++;
    }

    public Object get(int index) {
        return element[index];
    }

    //根据下标删除
    public Object remove(int index) {
        //如果是最后一个元素直接删除
        if (index == size - 1) {
            size--;
            return element[index];
        }
        //数组后续元素前移
        Object obj = element[index];
        for (int i = index; i < size; i++) {
            element[i] = element[i + 1];
        }
        size--;
        return obj;
    }

    //根据数据删除
    public Object remove(Object obj) {
        //判断对象是否存在
        for (int i = 0; i < element.length; i++) {
            if (element[i] != null && obj == element[i]) {
                return remove(i);
            }
        }
        return null;//数组中没有该对象
    }

}
