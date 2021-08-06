package com.Java.task10301;

/**
 * Author：CY
 * 1.编写一个方法，将一段文本中的各个单词的字母顺序翻转，
 * 例如："I like writing code best"，将变成"I ekil gnitirw edoc tseb"。
 * 2. String s="name=王五 age=18 classNum=1101”;
 * 将上面的字符串中包含的信息存放到 Student（里面有 name，age，classNum 三个属性）的对象中:
 * 3. 字符串压缩:利用字符重复出现的次数，编写一种方法，实现基本的字符
 * 串压缩功能。比如,字符串“aabccdd”会变成“a2b1c2d2”。若“压缩”以后的字符串没有变短，则返回原先的字符串。
 * Description：<描述>
 */
public class StringTest {

    public static void main(String[] args) {
        StringTest test = new StringTest();
        //System.out.println(test.StringReverse("I like writing code best"));
        //System.out.println(test.StudentTest("name=王五 age=18 classNum=1101"));
        //System.out.println(test.cpmpress("aabcccccddddd"));
        //System.out.println(test.cpmpress("abcdd"));
        System.out.println(test.changeTrim("   sdfhs aerg    "));
    }

    public String StringReverse(String str) {
        String[] strArr = str.split(" ");//将string按照空格分割成数组
        StringBuilder sb = new StringBuilder();//创建用于返回结果的新对象
        for (String s : strArr) {
            StringBuilder stringBuilder = new StringBuilder(s);//将数组中每个string字段都转化成stringBUffer
            stringBuilder.reverse();//反转数组中的每个字段
            sb.append(stringBuilder).append(" ");//拼接反转后的字段，用空格隔开
        }
        return sb.toString();
    }

    public String StudentTest(String str) {
        Student student = new Student();
        //String str = "name=王五 age=18 classNum=1101";
        String[] strArr = str.split(" ");//将string按照空格分割成数组
        for (int i = 0; i < strArr.length; i++) {
            String s = strArr[i];
            String[] tmp = s.split("=");
            switch (i){
                case 0:
                    student.setName(tmp[1]);
                    break;
                case 1:
                    student.setAge(Integer.valueOf(tmp[1]));
                    break;
                case 2:
                    student.setClassNum(Integer.valueOf(tmp[1]));
                    break;
            }
        }
        return student.toString();
    }

    public String cpmpress(String str) {
        if (str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char ch = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
             if (ch == str.charAt(i)) {
                 count++;
             } else {
                 sb.append(ch).append(count);
                 ch = str.charAt(i);
                 count = 1;
             }
        }
        sb.append(ch).append(count);
        return sb.length() > str.length() ? str:sb.toString();
    }

    public String changeTrim(String str) {
        int begin = 0;
        int end = str.length()-1;
        for (int i = 0; i < str.length(); i++) {
             if (str.charAt(i) != ' '){
                 begin = i;
                 break;
             }
        }
        for (int i = str.length()-1; i >= 0; i--) {
            if (str.charAt(i) != ' '){
                end = i;
                break;
            }
        }
        return str.substring(begin, end+1);
    }

}
