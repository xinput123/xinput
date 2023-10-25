package com.xinput.leet.string;

/**
 * 541: 反转字符串 https://leetcode.cn/problems/reverse-string-ii/
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * 示例 1：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * <p>
 * 示例 2：
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文组成
 * 1 <= k <= 104
 */
public class Code541 {
    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefg", 3));
    }

    public static String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i = i + 2 * k) {
            int start = i;
            int end = Math.min(s.length() - 1, start + k - 1);
            while (start < end) {
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
                start++;
                end--;
            }
        }

        return new String(chars);
    }
}