package com.Java.task10205.dao;

import com.Java.task10205.entity.Express;

import java.util.Random;

/**
 * Author：CY
 * DATE：dao层
 * Description：<描述>
 */
public class ExpressDao {
    private Express[][] expressArr = new Express[10][10];//快递柜
    private int size = 0;
    //当前存储的快递数
    /*private int size;
    {
        for (int i = 0; i < 10; i++) {
            expressArr[i] = new Express[10];
        }
    }*/
    
    private Random random = new Random();

    private int randomCode() {
        int code;
        while (true) {
            code = random.nextInt(90000) + 100000;
            Express express = findByCode(code);
            if (express == null) {
                return code;
            }
        }

    }

    /**
     * 用于存储快递
     * @param express
     * @return
     */
    public boolean add(Express express) {
        if (size == 100) {
            System.out.println("快递柜已满");
            return false;
        }
        //1.随机生成2个0——9的下标
        int x = -1;
        int y = -1;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
        } while (expressArr[x][y] != null);
        //2.取件码
        int code = randomCode();
        express.setCode(code);
        expressArr[x][y] = express;
        size++;
        return true;
    }

    public void delete(Express express) {
        p:for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (express.equals(expressArr[i][j])) {
                    expressArr[i][j] = null;
                    break p;
                }
            }
        }
    }

    public void update(Express oldExpress, Express newExpress) {
        delete(oldExpress);
        add(newExpress);
    }

    public Express[][] findAll() {
        return expressArr;
    }

    public Express findByNumber(String number) {
        Express express = new Express();
        express.setNumber(number);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (express.equals(expressArr[i][j])) {
                    return expressArr[i][j];
                }
            }
        }
        return null;
    }

    /**
     * 根据取件码查询快递
     * @param code
     * @return
     */
    public Express findByCode(int code) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (expressArr[i][j] != null) {
                    if (expressArr[i][j].getCode() == code) {
                        return expressArr[i][j];
                    }
                }
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
