package com.xinput.leet.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和 https://leetcode.cn/problems/3sum/description/
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * <p>
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * <p>
 * 提示：
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class Code015 {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{0, 0, 0}));
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{0, 1, 1}));
        System.out.println(threeSum(new int[]{0, 1, 1, -1}));
    }

    // 排序 + 双指针
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> numList = new ArrayList();
        if (nums.length < 3) {
            return numList;
        }

        Arrays.sort(nums); // 排序
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) { // 说明不可能小于0了
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重,预防 [0,0,-1,1]这种
            }

            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    numList.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 做去重
                    while (l < r && nums[l] == nums[l + 1]) {
                        l = l + 1;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r = r - 1;
                    }
                    l = l + 1;
                    r = r - 1;
                } else if (sum > 0) {
                    r = r - 1;
                } else {
                    l = l + 1;
                }
            }
        }

        return numList;
    }
}
