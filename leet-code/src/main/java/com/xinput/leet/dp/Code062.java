package com.xinput.leet.dp;

/**
 * https://leetcode.cn/problems/unique-paths/
 * 62: 不同路径
 */
public class Code062 {

    /**
     * 1、定义数组元素的含义
     * 计算从左上角到右下角有多少种路径，所以我们定义 dp[i][j] 的含义就是机器人走到(i,j)有多少种路径
     * <p>
     * 2、找出数组之间的关系式
     * 机器人如何走到 (i,j) 这个位置呢？因为机器人只能想左走，或者向有走，所以有两种方式可以到达
     * 一种是从 (i-1,j) 这个位置往下走一步到达
     * 一种是从 (i,j-1) 这个位置往右走一步到达
     * 所以 dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * <p>
     * 3、找出初始条件
     * 当 dp[i][j] 中 i 或者 j 有一个为 0 时，这个关系式是不能成立的，相当于一直靠边走
     * dp[0][0...n-1] = 1; 机器人只能一直往右走
     * dp[0...n-1][0] = 1; 机器人只能一直往下走
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7)); // 28
        System.out.println(uniquePaths(3, 2)); // 3
        System.out.println(uniquePaths(7, 3)); // 28
        System.out.println(uniquePaths(3, 3)); // 6
    }

    static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) { // 最上面一层，即一直往右走
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) { // 最左边一层，即一直往下走
            dp[0][i] = 1;
        }

        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
