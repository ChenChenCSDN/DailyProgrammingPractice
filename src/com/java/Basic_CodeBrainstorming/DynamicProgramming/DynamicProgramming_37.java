package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_37
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/25 15:49
 * @Version 1.0
 */
public class DynamicProgramming_37 {
    public static void main(String[] args) {
        new Solution37().minDistance("horse", "ros");
    }
}

class Solution37 {
    /**
     * 动态规划：本题与上一题的两个字符串的删除操作题目类似，只不过上一题是s1和s2只能删字符，而本题可以删字符、替换字符和修改字符，故要分情况进行讨论。
     * 确定dp数组及其下标含义：dp[i][j]表示以下标i为结尾(包括)的字符串s1，和以下标j为结尾(包括)的字符串s2的最近编辑距离。
     * 确定dp数组递推公式：当s1[i]=s2[j]时，元素相同，则保持之前状态即可，不用操作：dp[i][j] = dp[i-1][j-1]，当s1[i]!=s2[j]时，元素不同，此时有三种操作：1.删除元素，2.添加元素，3.替换元素，其中删除元素即删除s1对应的元素：dp[i-1][j] + 1，s1添加元素相当于s2减去元素，因为添加
     * 元素是指s1添加一个元素后和s2相同，故等同于s2减去一个元素和s1相同，替换元素即当前s1的元素替换成与s2的当前元素，相当于i-1，j-1来加一个元素，故分别对应：dp[i-1][j] + 1，dp[i][j-1] +1，dp[i-1][j-1] + 1，取最小值即可。
     * 确定dp数组初始化：记录初始元素是否有相同，然后进行赋值即可。
     *
     * @param word1 字符串s1
     * @param word2 字符串s2
     * @return 将s1转换成s2的最少操作数
     */
    public int minDistance(String word1, String word2) {
        //边界情况：s1为空或s2为空
        if (word1.isEmpty())
            return word2.length();
        if (word2.isEmpty())
            return word1.length();

        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();

        //初始化dp数组
        int[][] dp = new int[s1.length][s2.length];
        //记录相同元素的情况
        int count = 0;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == s2[0])
                count = 1;
            dp[i][0] = i + 1 - count;
        }

        count = 0;
        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == s1[0])
                count = 1;
            dp[0][i] = i + 1 - count;
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j])//当前元素相同
                    dp[i][j] = dp[i - 1][j - 1];
                else//当前元素不同，取删除、替换和添加三者的最小值
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
        return dp[s1.length - 1][s2.length - 1];
    }
}
