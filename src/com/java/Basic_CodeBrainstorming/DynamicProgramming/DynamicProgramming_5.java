package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_5
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/7/29 18:29
 * @Version 1.0
 */
public class DynamicProgramming_5 {
    public static void main(String[] args) {
//        new Solution5().uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        new Solution5().uniquePathsWithObstacles(new int[][]{{0, 0}, {1, 1}, {0, 0}});
    }
}

class Solution5 {
    /**
     * 动态规划
     * 确定dp数组及其下班含义：dp[i][j]表示从(0,0)到(i,j)的路径数目
     * 确定dp递推公式：由于只能向右和向下走，故dp[i][j]可由两个方向得到，分别是dp[i-1][j]和dp[i][j-1]
     * 确定dp数组初始化：由于只能向右和向下走，因此左上角的那一列和右上角的那一行只能有1条路径到达，但如果中间存在障碍物，会导致该后面的点都无法到达，dp[i][j]需置为0
     * 确定遍历顺序：从左向右，从上到下依次遍历，若遇障碍物，则置dp[i][j]为0
     * 时间复杂度：O(m*n)，空间复杂度：O(m*n)
     *
     * @param: obstacleGrid
     * @return: int
    **/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int x = obstacleGrid.length;
        int y = obstacleGrid[0].length;
        int[][] dp = new int[x][y];
//        for (int i = 0; i < y; i++) {
//            if (obstacleGrid[0][i] == 1) {
//                dp[0][i] = 0;
//                break;
//            } else
//                dp[0][i] = 1;
//        }
        //针对上述代码的优化：在for遍历过程中判断是否有障碍，发现有障碍则停止初始化dp数组，使其为默认的初始值0，即无法到达
        for (int i = 0; i < y && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
//        for (int i = 0; i < x; i++) {
//            if (obstacleGrid[i][0] == 1) {
//                dp[i][0] = 0;
//                break;
//            } else
//                dp[i][0] = 1;
//        }
        //针对上述代码的优化：在for遍历过程中判断是否有障碍，发现有障碍则停止初始化dp数组，使其为默认的初始值0，即无法到达
        for (int i = 0; i < x && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //遍历dp数组，验证是否满足预期
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[x - 1][y - 1];
    }
}
