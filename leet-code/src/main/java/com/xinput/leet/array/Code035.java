package com.xinput.leet.array;

/**
 * 35、搜索插入位置 https://leetcode.cn/problems/search-insert-position/
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class Code035 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 0;
        System.out.println(searchInsert2(nums, target));
    }

    // 暴力破解，O(n)
    static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && target < nums[i]) {
                return i;
            }

            if (i == nums.length - 1 && target > nums[i]) {
                return i + 1;
            }

            if (nums[i] == target) {
                return i;
            } else if (nums[i] > target && nums[i - 1] < target) {
                return i;
            }
        }
        return 0;
    }

    // 二分查找
    static int searchInsert2(int[] nums, int target) {
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left..mid]
                right = mid;
            }
        }
        return left;
    }
}
