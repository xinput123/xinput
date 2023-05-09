package com.xinput.leet.array;

import java.util.HashMap;

/**
 * 1. 两数之和
 * https://leetcode.cn/problems/two-sum/
 * <p>
 * 要求：
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * <p>
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * <p>
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 */
public class Code001 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;

    }

    // 数组双循环
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int targetValue = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == targetValue) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // 利用 HashMap
    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            int targetValue = target - nums[i];
            if (map.containsKey(targetValue)) {
                firstIndex = i;
                secondIndex = map.get(targetValue);
            }
            map.put(nums[i], i);
        }

        if (firstIndex > secondIndex) {
            return new int[]{secondIndex, firstIndex};
        }
        return new int[]{firstIndex, secondIndex};
    }
}