package com.xinput.leet.dp;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 * 70：爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * 提示：
 * 1 <= n <= 45
 */
public class Code070 {

    /**
     * 这个题目属于比较的简单的一维数据
     * 1、定义数组元素的含义
     * dp[n]的含义，爬到 n 阶楼梯有多少种方法
     * <p>
     * 2、找出数组之间的关系式
     * 爬到第n阶的方式，因为每次可以爬 1 或 2 个台阶，所以可以从 第 n-1 阶 或者 n-2 阶上来
     * 所以 dp[n] = dp[n-1] + dp[n-2]
     * <p>
     * 3、找出初始条件
     * dp[0] = 0
     * dp[1] = 1
     * dp[2] = 2
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(4));
    }

    static int climbStairs(int n) {
        if (n <= 1) {
            return n;
        }

        if (n == 2) {
            return 2;
        }

        // 创建数组 d[n] 用于保存历史数据
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
