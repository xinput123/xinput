package com.xinput.leet.dp;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 * 509. 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * <p>
 * 示例 3：
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * 提示：
 * 0 <= n <= 30
 */
public class Code509 {

    public static void main(String[] args) {
//        System.out.println(fib(3));
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
    }

    static int minCostClimbingStairs(int[] cost) {
        int n = cost.length; // 楼梯长度

        int[] dp = new int[n]; // dp[i] 代表爬到 i 阶台阶时的最小费用
        dp[0] = 0;
        dp[1] = Math.min(cost[0], cost[1]);
//        dp[2] = Math.min(cost[0], cost[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i], dp[i - 1]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }

    /**
     * 1、定义数组元素的含义
     * 要求： 斐波那契数
     * dp[i] 表示以 f(n) 的值
     * 2、找出数组元素之间的关系
     * dp[n] = dp[n-1] + dp[n+2]
     * <p>
     * 3、找出初始值
     * 值不能小于0，否则没有意义
     * dp[0] = 0
     * dp[1] = 1
     */
    static int fib(int n) {
        if (n < 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
