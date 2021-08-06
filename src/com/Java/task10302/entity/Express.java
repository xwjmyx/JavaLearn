package com.Java.task10302.entity;

import java.util.Objects;

/**
 * Author：CY
 * 实体类
 * Description：<描述>
 */
public class Express {

    private String number;//快递单号
    private String company;//快递公司
    private int code;//取件码
    private int locateNum;//位置号码

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getLocateNum() {
        return locateNum;
    }

    public void setLocateNum(int locateNum) {
        this.locateNum = locateNum;
    }

    public Express() {
    }

    public Express(String number, String company, int code, int locateNum) {
        this.number = number;
        this.company = company;
        this.code = code;
        this.locateNum = locateNum;
    }

    @Override
    public String toString() {
        return "快递信息{" +
                "快递单号：" + number +
                "，快递公司：" + company +
                "，取件码：" + code +
                "，第" + locateNum +
                "号柜子}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Express express = (Express) o;
        return code == express.code && locateNum == express.locateNum && number.equals(express.number) && company.equals(express.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, company, code, locateNum);
    }
}
