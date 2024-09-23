package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_12
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/6 14:44
 * @Version 1.0
 */
public class DynamicProgramming_12 {
    public static void main(String[] args) {
        System.out.println(new Solution12().findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }
}

class Solution12 {
    /**
     * 动态规划：本题的含义是从strs数组中选取子集，使其子集的个数最大，限制条件是所有子集中0和1的个数总和有要求，因此可以转化为01背包问题，从字符串数组中任取子集（每个元素只能取一次），
     * 限制条件是所取子集数组的0和1的个数总和。
     * 确定dp数组及其下标含义：dp[i][j][k]表示从下标0~i的字符串数组中任取字符串放入背包含有j个0和k个1的字符串个数，其中dp[strs.length][m][n]就是从所有字符串中任选，放入最多有m个0和n个1的背包的最大子集个数。
     * 之所以可以使用m和n来充当背包的限定条件，是因为每个字符串中由0和1组成，因此限制背包中0和1的总和个数也就是限制选取字符串的条件。
     * 注：dp[i][j][k] 表示在前 i 个字符串中，使用 j 个 0 和 k 个 1 的情况下最多可以得到的字符串数量（官方解释）
     * 确定dp递推公式：同01背包问题，dp[i][j][k]可由两个方向得来：dp[i-1][j][k]和dp[i-1][j-zeroNum][k-oneNum]，其中zeroNum和oneNum是指当前选中的字符串含有的0和1的个数总和，
     * dp[i-1][j][k]表示不选取当前字符串，最多有j个0和k个1的最大子集的大小，dp[i-1][j-zeroNum][k-oneNum]表示选取当前字符串，[j-zeroNum][k-oneNum]表示未选取该字符串前最大子集的个数，则选取了该字符串后子集个数+1：dp[i-1][j-zeroNum][k-oneNum]+1
     * 又因为是要求最大子集的情况，有可能不选取当前字符串的情况下能够放入更多的子集，因此dp[i][j][k] = max(dp[i-1][j][k],dp[i-1][j-zeroNum][k-oneNum]+1).
     * 确定dp数组初始化及遍历方式：当只放入第一个字符串时，判断其包含的0和1个数是否大于要求，若if (zeroNum <= i && oneNum <= j)，则dp[0][i][j] = 1（即只选取第一个字符串来组成子集），
     * 同时先遍历字符串，后遍历0和1的个数
     * 时间复杂度：O(m*n*k)，空间复杂度：O(m*n*k)
     *
     * @param strs 待查找的字符串数组
     * @param m    0的最大个数
     * @param n    1的最大个数
     * @return 满足条件的最大子集个数
     */
    public int findMaxForm(String[] strs, int m, int n) {
        //定义dp数组
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        //zeroNum记录当前字符串中0的总和，oneNum记录当前字符串中1的总和
        int zeroNum = 0, oneNum = 0;

        //计算第一个字符串中0和1的分布情况，用于dp数组初始化
        for (char c : strs[0].toCharArray()) {
            if (c == '0') zeroNum++;
            else oneNum++;
        }
        //dp数组初始化
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (zeroNum <= i && oneNum <= j)//当前背包的i和j个数大于第一个字符串中的zeroNum和oneNum，子集为当前字符串，个数为1
                    dp[0][i][j] = 1;
            }
        }

        //遍历字符串
        for (int i = 1; i < strs.length; i++) {
            //更新，只记录当前使用到的字符串的0和1分布情况
            zeroNum = 0;
            oneNum = 0;
            //计算当前字符串0和1的分布情况
            for (char c : strs[i].toCharArray()) {
                if (c == '0') zeroNum++;
                else oneNum++;
            }
            //先遍历0的情况，后遍历1的情况（顺序可替换）
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    if (j < zeroNum || k < oneNum)//当前背包的i和j个数小于放入字符串中的zeroNum和oneNum，不放入
                        dp[i][j][k] = dp[i - 1][j][k];
                    else    //能够放入当前字符串
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroNum][k - oneNum] + 1);
                }
            }
        }
        //dp[strs.length - 1][m][n]即从前strs.length个字符串中任取，放入限制条件为m和n的最大子集个数
        return dp[strs.length - 1][m][n];
    }

    /**
     * 动态规划：三维数组优化成二维数组，根据01背包的二维遍历情况可知，当前层的情况只与上一层的情况有关，因此可以使用二维滚动数组，来临时记录上一层的情况并在此基础上进行更新，
     * 由于二维动态数组要在记录上一层的情况下进行更新，因此需要从后往前遍历数组，这样才能不影响之前记录的情况。
     * 确定dp数组及其下标含义：dp[i][j]表示  从所选取的字符串中放入最多有i个0和j个1的背包的最大子集个数
     * 时间复杂度：O(m*n*k)，空间复杂度：O(n*k)
     *
     * @param strs 待查找的字符串数组
     * @param m    0的最大个数
     * @param n    1的最大个数
     * @return 满足条件的最大子集个数
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        //定义dp数组
        int[][] dp = new int[m + 1][n + 1];

        //先遍历字符串
        for (String str : strs) {
            //zeroNum记录当前字符串中0的总和，oneNum记录当前字符串中1的总和
            //更新，只记录当前使用到的字符串的0和1分布情况
            int zeroNum = 0, oneNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else
                    oneNum++;
            }
            //先遍历m，后遍历j，且必须从后往前遍历
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }

        //打印dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //dp[m][n]即从所有字符串中任取，放入限制条件为m和n的最大子集个数
        return dp[m][n];
    }
}
