package com.xx.xinput.java.lang;

import java.lang.reflect.Field;

/**
 * String 是不是真的不能被修改
 */
public class StringDemo {

    public static void main(String[] args) throws Exception {
        String value = "Hello"; // 常量（语法特性） = 对象类型常量化

        // 面向对象规则：一切对象需要 new
        // 合法的写法（会被视作异类）
        String value2 = new String("Hello");

        System.out.println("value :　" + value);
        System.out.println("value2 :　" + value2);

        // 从 Java 1.5 以后开始对象属性可以直接通过反射修改
        char[] chars = "World".toCharArray();

        // 获取 String 类中的 value 字段
        Field valueField = String.class.getDeclaredField("value");
        // 设置 private 字段可以被修改
        valueField.setAccessible(true);
        valueField.set(value2, chars);
        System.out.println("value :　" + value);
        System.out.println("value2 :　" + value2);
    }
}
