package com.xinput.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    /**
     * 打印集合
     */
    public static void print(List objectList) {
        System.out.println(objectList.toString());
        System.out.println();
    }

    public static List<Integer> newArrayList(int... nums) {
        List<Integer> list = new ArrayList();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }
}
