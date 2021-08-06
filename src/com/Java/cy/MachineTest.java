package com.Java.cy;

import java.util.Scanner;

/**
 * Author：CY
 * Description：<描述>
 */
public class MachineTest {
    public static void main(String[] args) {
        int num;
        int ran;
        int player = 0;
        int machine = 0;
        Machine mch = new Machine();
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("请输入:剪刀：1、石头：2、布：3");
            num = input.nextInt();
            ran = mch.randomNum();
            if (ran == num) {
                System.out.println("playr:" + num + ",machine:" + ran);
                System.out.println("第" + (i + 1) + "局比分：" + "playr:" + player + ",machine:" + machine);
                continue;
            } else if (num == 1 && ran == 3 || num == 2 && ran == 1 || num == 3 && ran == 2) {
                player++;
                System.out.println("playr:" + num + ",machine:" + ran);
                System.out.println("第" + (i + 1) + "局比分：" + "playr:" + player + ",machine:" + machine);
                continue;
            }else {
                machine++;
                System.out.println("playr:" + num + ",machine:" + ran);
                System.out.println("第" + (i + 1) + "局比分：" + "playr:" + player + ",machine:" + machine);
                continue;
            }

        }
        System.out.println("游戏结束！最终比分：" + "playr:" + player + ",machine:" + machine);
    }
}
