package com.xinput.leet.dp;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/maximum-subarray/
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class Code053 {

    public static void main(String[] args) {
        maxSubArray(new int[]{1, 2, 3});
        maxSubArray(new int[]{1, -3, 2, 4});
        maxSubArray(new int[]{1, -3, -2, 4});
        maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }

    /**
     * 1、定义数组元素的含义
     * 要求： 找出一个具有最大和的连续子数组，返回其最大和.
     * dp[i] 表示以 num[i] 结尾的子段的最大最短和
     * 2、找出数组元素之间的关系
     * dp[n] 的最大值肯定和 dp[n-1] 以及 num[n] 有关
     * <p>
     * 3、找出初始值
     */
    static int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        System.out.println(Arrays.toString(dp));
        return max;
    }
}
