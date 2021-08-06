package com.Java.cy;

import java.util.Random;

/**
 * Author：CY
 * 1. 定义机器类，以及拳头属性(此属性只有三个值:剪刀，石头，布。这里的值可以使用数值代替)
 * 2. 定义生成随机数的方法(让机器生成剪刀，石头，布的值)，赋值给第一步的拳头属性
 * 3. 定义测试类，获取用户输入的剪头石头布的值，和随机生成的值比较
 * 4. 测试中，定义变量保存胜者积分
 * Description：<描述>
 */
public class Machine {
    private int fist;

    public int randomNum() {
        Random random = new Random();
        fist = random.nextInt(3) + 1;
        return fist;
    }
}
