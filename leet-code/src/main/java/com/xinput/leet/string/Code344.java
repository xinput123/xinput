package com.xinput.leet.string;

import java.util.Arrays;

/**
 * 344: 反转字符串 https://leetcode.cn/problems/reverse-string/
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 示例 1：
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * <p>
 * 示例 2：
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
public class Code344 {
    public static void main(String[] args) {
        char[] s = new char[]{'a', 'b', 'c'};
        reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    // 反转字符串，即不停的头尾交换
    public static void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}