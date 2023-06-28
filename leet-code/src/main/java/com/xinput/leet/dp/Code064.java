package com.xinput.leet.dp;

/**
 * https://leetcode.cn/problems/minimum-path-sum/
 * 64: 最小路径和
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */
public class Code064 {

    /**
     * 1、定义数组元素的含义
     * 题目要求我们计算从左上角到右下角的最小路径，所以我们定义 dp[i][j] 的含义为: 当机器人走到 (i,j) 这个位置时，最小的路径和是 dp[i][j].
     * <p>
     * 2、找出数组之间的关系式
     * 机器人如何走到 (i,j) 这个位置呢？因为机器人只能想左走，或者向有走，所以有两种方式可以到达
     * 一种是从 (i-1,j) 这个位置往下走一步到达
     * 一种是从 (i,j-1) 这个位置往右走一步到达
     * 我们计算的是所有可能的路径，哪一个路径和是最小的，那么我们就需要从这两种方式中，选择一种，使得 dp[i][j] 的值是最小的，
     * 所以我们可以推导出 dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + arr[i][j]; arr[i][j] 代表的是网格中的值
     * <p>
     * 3、找出初始条件
     * 当 dp[i][j] 中 i 或者 j 有一个为 0 时，这个关系式是不能成立的，相当于一直靠边走
     * 最上面一行，一直往右走 dp[0][j] = dp[0][j-1] + arr[0][j]
     * 最左边一行，一直往下走 dp[i][0] = dp[i-1][0] + arr[i][0]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] a = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(uniquePaths(a));
        int[][] b = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(uniquePaths(b));
    }

    static int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) { // 最上面一层，即一直往右走
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < n; i++) { // 最左边一层，即一直往下走
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
