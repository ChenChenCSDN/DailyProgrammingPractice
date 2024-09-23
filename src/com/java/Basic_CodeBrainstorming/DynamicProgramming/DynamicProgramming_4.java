package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_4
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/7/29 17:59
 * @Version 1.0
 */
public class DynamicProgramming_4 {
    public static void main(String[] args) {
        new Solution4().uniquePaths(3, 4);
    }
}

class Solution4 {
    /**
     * 动态规划
     * 确定dp数组及其下标含义：dp[i][j]表示从(0,0)到(i,j)有dp[i][j]条路径
     * 确定dp递推公式：因为只能向右和向下走，故dp[i][j]可以由两个方向得到：dp[i-1][j]和dp[i][j-1]，易得dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 确定dp初始化：因为只能向右和向下走，因此到达左下那一列和右上那一行的路径只有1条，因此dp[0~m][0] = 1，dp[0][0~n] = 1
     * 确定遍历顺序：易得从左到右从上到下以此遍历网格即可
     * 时间复杂度：O(m*n)，空间复杂度：O(m*n)
     *
     * @param n 网格的列数
     * @param: m 网格的行数
     * @return: int 从（0，0）到（m-1，n-1）的路径数
     **/
    public int uniquePaths(int m, int n) {
        //dp[i][j]:(0,0)到(i,j)的路径数
        int[][] dp = new int[m][n];
        //dp数组初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //遍历网格
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //遍历dp数组，验证dp数组是否正确
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[m - 1][n - 1];
    }
}
