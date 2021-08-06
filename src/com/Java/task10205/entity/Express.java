package com.Java.task10205.entity;

import java.util.Objects;

/**
 * Author：CY
 * DATE：实体类
 * Description：<描述>
 */
public class Express {

    private String number;//快递单号
    private String company;//快递公司
    private int code;//取件码

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

    public Express() {
    }

    public Express(String number, String company, int code) {
        this.number = number;
        this.company = company;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Express{" +
                "number='" + number + '\'' +
                ", company='" + company + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Express express = (Express) o;
        return Objects.equals(number, express.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
