package com.xinput.leet.sort;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组 https://leetcode.cn/problems/merge-sorted-array/
 * <p>
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * <p>
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * <p>
 * 示例 3：
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 * <p>
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 * <p>
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 */
public class Code88 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 0, 0, 0, 0};
        int[] b = new int[]{0, 2, 4, 6};
        merge2(a, 3, b, 4);
    }

    /**
     * 因为数组1位数足够，直接将 num2 放入num1 中，然后在排序
     *
     * @param nums1 数组1
     * @param m     数组1 中的元素个数
     * @param nums2
     * @param n     数组2 中的元素个数
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 逆向双指针，因为数组 num1 尾部是空的，并且数组1的长度是正好可以将 数组2的数据也填充进去的
     * 所以我们只需要每次取出 num1 和 num2 数组中的最后一个进行比较即可
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        for (int i1 = nums1.length - 1; i1 >= 0; i1--) {
            Integer num1 = m < 1 ? null : nums1[m - 1];
            Integer num2 = n < 1 ? null : nums2[n - 1];
            if (null != num1 || null != num2) {
                if (null == num1) {
                    nums1[i1] = num2;
                    n = n - 1;
                } else if (null == num2) {
                    nums1[i1] = num1;
                    m = m - 1;
                } else {
                    if (num1 > num2) {
                        nums1[i1] = num1;
                        m = m - 1;
                    } else {
                        nums1[i1] = num2;
                        n = n - 1;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(nums1));
    }
}
