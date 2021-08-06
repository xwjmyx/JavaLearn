package com.Java.task10301002;


import com.Java.task10301.Student;

import java.util.*;

/**
 * Author：CY
 * 分别用Comparable和Comparator两个接口对下列四位同学的成绩做降序排序，如果成绩一样，那在成绩排序的基础上按照年龄由小到大排序。
 * Description：<描述>
 */
public class Compare {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person("贾宝玉", 14, 88.5);
        Person p2 = new Person("林黛玉", 13, 90.5);
        Person p3 = new Person("史湘云", 13, 85);
        Person p4 = new Person("薛宝钗", 15, 91);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        System.out.println("正常顺序");
        for (Person p : list) {
            System.out.println(p);
        }

        Collections.sort(list);
        System.out.println("compare接口排序");
        for (Person p : list) {
            System.out.println(p);
        }

        System.out.println("comparator接口排序");
        Collections.sort(list, new PersonComparator());
        for (Person p : list) {
            System.out.println(p);
        }

    }

    static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            if (p1.score > p2.score) {
                return -1;
            } else if (p1.score == p2.score) {
                if (p1.age > p2.age) {
                    return -1;
                }
                return 1;
            }
            else
                return 1;
        }

    }



    static class Person implements Comparable<Person> {
        private String name;
        private int age;
        private double score;

        public Person(String name, int age, double score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", score=" + score +
                    '}';
        }

        @Override
        public int compareTo(Person p) {
            if (this.score < p.score){
                return 1;
            } else if (this.score == p.score) {
                if (this.age < p.age) {
                    return 1;
                }
                else {
                    return -1;
                }
            } else
                return -1;
        }

        /*@Override
        public int compareTo(Person s) {
            if (this.score == s.score){
                return this.age - s.age;
            }
            if (this.score - s.score > 0){
                return -1;
            }else {
                return 1;
            }
        }*/


    }


}
