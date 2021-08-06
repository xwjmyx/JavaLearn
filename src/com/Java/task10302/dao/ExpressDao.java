package com.Java.task10302.dao;

import com.Java.task10302.entity.Express;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 * Author：CY
 * dao层
 * Description：<描述>
 */
public class ExpressDao {

    //用的是TreeMap,因为可以在输出所有快递的时候自动排序，让程序看着更加美观
    static Map<String, Express> expressMap = new TreeMap<>();//快递柜,key为快递单号，value为快递信息

    //初始化快递柜，默认添加几个快递
    public ExpressDao() {
        expressMap.put("1001",new Express("1001","中通快递",123456,1));
        expressMap.put("1002",new Express("1002","圆通快递",456789,2));
        expressMap.put("1003",new Express("1003","顺丰速递",123789,6));
    }
    
    private Random random = new Random();

    public Map<String, Express> getExpressMap() {
        return expressMap;
    }

    //随机生成6位取件码
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
     * 存储快递
     * @param express
     * @return
     */
    public boolean add(Express express) {
        if (expressMap.size() == 100) {
            System.out.println("快递柜已满！");
            return false;
        }
        //1.随机生成2个0——9的下标
        int num = -1;
        do {
            num = random.nextInt(100);
        } while (isExistNum(num));
        //2.取件码
        int code = -1;
        do {
            code = randomCode();
        } while (isExistCode(code));
        express.setCode(code);
        express.setLocateNum(num);
        expressMap.put(express.getNumber(), express);
        return true;
    }

    //equals方法重写失败，对象无法比较
    /*public void delete(Express express) {
        for (Express e : expressMap.values()) {
            boolean res = express.equals(expressMap.values());
            if (res) {
                expressMap.remove(e);
                break;
            }
        }
    }*/

    /**
     * 删除快递
     * @param number
     */
    public void delete(String number) {
        expressMap.remove(number);
    }

    //equals方法重写失败，对象无法比较
    /*public void update(Express oldExpress, Express newExpress) {
        delete(oldExpress);
        add(newExpress);
    }*/

    /**
     * 修改快递，number为旧快递单号
     * @param number
     * @param newExpress
     */
    public void update(String number, Express newExpress) {
        delete(number);
        add(newExpress);
    }

    /**
     * 根据单号查询快递
     * @param number
     * @return
     */
    public Express findByNumber(String number) {
        Express e = new Express();
        e.setNumber(number);
        for (Express express : expressMap.values()) {
            if (express.getNumber().equals(number)) {
                return express;
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
        for (Express express : expressMap.values()) {
            if (express.getCode() == code) {
                return express;
            }
        }
        return null;
    }

    //快递单号是否存在
    public boolean isExistNum(int num) {
        for (Express express : expressMap.values()) {
            if (express.getLocateNum() == num )
                return true;
        }
        return false;
    }

    //取件码是否存在
    public boolean isExistCode(int code) {
        for (Express express : expressMap.values()) {
            if (express.getCode() == code )
                return true;
        }
        return false;
    }
}
