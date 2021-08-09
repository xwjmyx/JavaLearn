package com.Java.cy;

import java.io.File;
import java.io.IOException;

/**
 * Author：CY
 * DATE：2021-08-2021/8/9 16:34
 * Description：<描述>
 */
public class Test {
    public static void main(String[] args) throws IOException {
        File dir = new File("d://haha1");
        //boolean c = dir.mkdir();
        System.out.println(dir.mkdir()?"1":"2");

        File a = new File(dir,"a.txt");
        //a.createNewFile();
        File b = new File("d://haha1","b.txt");

        System.out.println(a.createNewFile()?"1":"2");
        System.out.println(b.createNewFile()?"1":"2");

    }
}
