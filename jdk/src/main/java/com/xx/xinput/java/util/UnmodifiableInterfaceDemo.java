package com.xx.xinput.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Unmodifiable 内部类的作用
 */
public class UnmodifiableInterfaceDemo {

    public static void main(String[] args) {
        Collection<Integer> values = of(1, 2, 3, 4, 5);
        values.add(6);
        System.out.println(values);

        values = unmodifiableOf(1, 2, 3, 4, 5);
        // 不允许修改
        // 线程安全(读或者写都是数据一致)
        // 只读必然是线程安全
        values.add(6); // 这里会抛出异常UnsupportedOperationException，因为不允许修改看
        System.out.println(values);
    }

    public static Collection<Integer> of(Integer... values) {
        return new ArrayList<Integer>(Arrays.asList(values));
    }

    public static Collection<Integer> unmodifiableOf(Integer... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
