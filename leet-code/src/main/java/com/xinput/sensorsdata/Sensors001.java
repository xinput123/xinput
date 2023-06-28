package com.xinput.sensorsdata;

/**
 * 题目：
 * 给定一个数字 arr，表示连续 n 天的股价，数组下标表示第几天
 * 指标X: 任意两天的股价之和 - 此两天间隔的天数
 * 返回 arr 中最大的指标X
 * <p>
 * 要求: 时间复杂度 O(n)
 * <p>
 * eg:
 * 第3天，价格是10
 * 第9天，价格是30
 * 那么第3天和第9天的指标 X = 10 + 30 -（9 - 3）= 34
 */
public class Sensors001 {
    public static void main(String[] args) {

    }

    /**
     * 解析：X = arr[i] + arr[j] - (j - i) => (arr[i] + i) + (arr[j] - j)
     * 比如 [7,6,9],
     * 则:
     * 0 + arr[0] = 7
     * 1 + arr[1] = 7
     * 2 + arr[2] = 11
     */
    static int maxX(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int preBest = arr[0];
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, arr[i] - i + preBest);
            preBest = Math.max(preBest, arr[i] + i);
        }

        return 0;
    }
}
