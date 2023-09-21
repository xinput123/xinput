package com.xinput.leet.array;

import com.xinput.util.ArrayUtils;
import com.xinput.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * https://leetcode.cn/problems/spiral-matrix/
 * <p>
 * 要求：
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class Code054 {
  public static void main(String[] args) {
    int[][] arrays = ArrayUtils.createArray(5, 3);
    ArrayUtils.print(arrays);
//        ArrayUtils.print(ArrayUtils.createArray(1, 2));
//        ArrayUtils.print(ArrayUtils.createArray(1, 3));
    ListUtils.print(spiralOrder(arrays));
  }


  /**
   * [1,   2,  3]
   * [4,   5,  6]
   * [7,   8,  9]
   * [10, 11, 12]
   * [13, 14, 15]
   */
  public static List<Integer> spiralOrder(int[][] matrix) {
    int rows = matrix.length;
    int columns = matrix[0].length;
    List<Integer> result = new ArrayList(columns * rows);

    /**
     * 起始位置
     * left 从左往右的起始位置
     * top 从上往下的起始位置
     * right 从右往左的起始位置
     * bottom 从小往上的起始位置
     */
    int top = 0, left = 0, right = columns - 1, bottom = rows - 1;
    while (true) {
      // 先遍历最上层-从左到右: 起始位置为left，到right截止，top同一行不变
      for (int i = left; i <= right; i++) {
        result.add(matrix[top][i]);
      }

      if (++top > bottom) { // 这里是为了top层遍历完后没有下一层了
        break;
      }

      // 再遍历最右层-从上到下: 起始位置为top，到bottom截止，right同一列不变
      for (int i = top; i <= bottom; i++) {
        result.add(matrix[i][right]);
      }
      if (--right < left) { // 这里是为了right层遍历完后没有
        break;
      }

      // 再遍历最下层-从右到左: 起始位置为right，到left截止，bottom同一行不变
      for (int i = right; i >= left; i--) {
        result.add(matrix[bottom][i]);
      }
      if (--bottom < top) {
        break;
      }

      // 最后遍历最做层-从下到上: 起始位置为bottom，到top截止，left同一列不变
      for (int i = bottom; i >= top; i--) {
        result.add(matrix[i][left]);
      }
      if (++left > right) {
        break;
      }
    }
    return result;
  }
}