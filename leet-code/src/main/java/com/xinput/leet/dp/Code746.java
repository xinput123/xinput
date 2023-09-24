package com.xinput.leet.dp;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 * 746、使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * 示例 1：
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * <p>
 * 示例 2：
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 * <p>
 * 提示：
 * <p>
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class Code746 {

    public static void main(String[] args) {
        minCostClimbingStairs(new int[]{10, 15, 20});
        minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
    }

    /**
     * 1、定义数组元素的含义
     * 要求： 第 i 个台阶向上爬需要支付的费用
     * dp[i] 代表 i 个台阶向上爬需要支付的费用
     * 2、找出数组元素之间的关系
     * dp[n] 的最大值肯定和 dp[n-1] 以及 num[n] 有关
     * <p>
     * 3、找出初始值
     */
    static int minCostClimbingStairs(int[] cost) {
        int n = cost.length; // 楼梯长度
        if (n == 0) {
            return 0;
        }

        if (n == 1) { // 只有一个台阶
            return 0;
        }

        /**
         * 创建长度为 n+1 的数组 dp，其中 dp[i] 表示达到下标 i 的最小花费。
         * 由于可以选择下标 0 或 1 作为初始阶梯，因此有 dp[0]=dp[1]=0。
         *
         * 当 i >= 2的时候, 可以从 i-1 的位置或者 i-2 的位置过去
         */
        int[] dp = new int[n + 1]; // dp[i] 代表爬到 i 阶台阶时的最小费用
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
