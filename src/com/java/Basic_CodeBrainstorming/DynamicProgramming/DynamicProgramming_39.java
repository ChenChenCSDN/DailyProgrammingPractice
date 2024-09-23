package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_40
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/26 11:59
 * @Version 1.0
 */
public class DynamicProgramming_39 {
    public static void main(String[] args) {
        new Solution39().longestPalindromeSubseq("bbbab");
    }
}

class Solution39 {
    /**
     * 动态规划：此题与上一题的求最长回文子串的长度问题相似，回文子串要求字符连续，故只对s[i]=s[j]的情况下进行处理，不相同则说明不满足回文子串的定义，不用再判断内部是否满足回文子串的定义，而本题是求回文子序列，不要求字符连续，因此当s[i]!=s[j]时也要进行判断，判断其内部是否满足回文子序列的定义。
     * 另外子序列的定义是不改变剩余字符顺序的情况下，删除某些字符而形成的序列，故不相同时，需要考虑删左边还是删右边。
     * 确定dp数组及其下标含义：dp[i][j]表示[i,j](闭区间且i<j)范围内最长的回文子序列长度。
     * 确定dp数组递推公式：当s[i]=s[j]时，子序列长度+2，找[i+1,j-1]范围内的子序列长度，然后加起来，即：dp[i][j] = dp[i+1,j-1] + 2，当s[i]！=s[j]时，此时可以删除元素：1.删除左边的元素，范围从[i+1,j]内查找，即：dp[i][j] = dp[i+1][j]。2.删除右边的元素，范围从[i,j-1]内查找，即：dp[i][j]=dp[i][j-1]
     * 又因为是求最大值，故dp[i][j] = max{dp[i+1][j],dp[i][j-1]}.
     * 确定dp数组初始化：因为i<j，故当i>j时元素值为0，而当i=j时根据dp数组含义可知dp[i][j]=1
     * 确定dp数组遍历顺序：dp[i][j]由dp[i+1,j-1]、dp[i+1][j]、dp[i][j-1]，故需从下往上，从左往右遍历。
     * 时间复杂度：O(n^2)，空间复杂度：O(n^2)
     *
     * @param s 字符串s
     * @return s内最长回文子序列的长度
     */
    public int longestPalindromeSubseq(String s) {
        //初始化dp数组，对角线元素赋值为1
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        //先从下往上遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            //后从左往右遍历
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {//当前字符相同，子序列长度+2，判断其内部子序列长度
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {//当前字符不同，判断删除字符情况下子序列的长度
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        //dp[0][s.length()-1]表示[0,s.length-1]范围内的最长回文子序列的长度
        return dp[0][s.length()-1];
    }
}
