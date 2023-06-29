package com.xinput.leet.dp;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/house-robber/
 * 198: 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Code198 {

    public static void main(String[] args) {
        int[] a = {4, 1, 1, 5, 1, 5};
        System.out.println(rob(a));
        System.out.println(rob1(a));
    }

    /**
     * 1、定义数组元素的含义
     * 要求能够偷到最高的金额，所以 dp[i] 代表能够偷到的最多的金额
     * 2、找出数组元素之间的关系
     * 如果不超过2两个房间，那么谁大选谁
     * 考虑第三个房间：
     * 如果偷第三个房间，则意味着第二个房间不偷，也就是第三个房间值 + 第一个房间值 > 第二个房间值
     * 如果不偷第三个房间值，则宝藏数量等于前两个房间的宝藏数量
     * 所以可以得出 dp[i] = Max(dp[i-2] + num[i], dp[i-1]);
     * 3、找出初始值
     * 只有一个房间，那只能偷第一个 d[0] = num(1);
     * 只有两个房间时，偷最大的，Max(num(0), num(1))
     *
     * @param nums 各个房间的宝藏
     */
    static int rob1(int[] nums) {
        int n = nums.length; // n 表示房间数量
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return nums[0];
        }

        int[] dp = new int[n]; // dp[i]表示在前i个房间偷到的最大宝藏数
        // 初始化
        dp[0] = nums[0]; // 只有一个房间
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }

    static int rob(int[] nums) {
        int n = nums.length;
        if (n <= 0) {
            return 0;
        }

        int[] dp = new int[n]; // dp[i]表示在前i个房间偷到的最大宝藏数
        // 初始化
        dp[0] = nums[0];
        if (n == 1) {
            return nums[0];
        }

        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }
}
