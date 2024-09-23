package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DynamicProgramming_38
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/26 10:17
 * @Version 1.0
 */
public class DynamicProgramming_38 {
    public static void main(String[] args) {
        new Solution38().countSubstrings("abaa");
    }
}

class Solution38 {
    /**
     * 动态规划：本题dp数组的定义与以往的不同，以往都是根据所求的结果进行定义dp数组的含义，但本题是求回文子串的个数，如果dp[i]表示从0到i结尾的字符串中回文字串的个数，会发现dp[i]和dp[i-1]、dp[i][j-1]没有什么关联，因此不能如此定义du数组的含义。
     * 根据分析可以发现回文字串是对两边的字符进行判断，如果当前字符串两头的字符是相同的，则判断内部的字符串是否是回文字符即可，因此整体的结果取决于内部部分的结果，因此可以根据字符串两边的结果定义dp数组的含义。
     * 确定dp数组及其下标含义：dp[i][j]表示[i,j]闭区间范围内的字符串是否是回文字符，其结果取决于[i+1,j-1]范围内个字符串是否是回文字串。
     * 确定dp数组递推公式：根据dp数组的含义，当s[i]！=s[j]时，那么该范围内肯定不是回文字符。当s[i]=s[j]时，需要判断[i+1,j-1]是否时回文字串，另外s[i]=s[j]时，i和j分三种情况：1.i==j，则dp[i][j]一定是回文子串，2.i+1=j，即i和j相邻的情况，又有s[i]=s[j]，则dp[i][j]一定是回文字串。
     * 3.i+1<j时，即i和j相隔距离大于1时，dp[i][j]的情况取决于dp[i+1][j-1]的情况。
     * 确定dp数组初始化：初始化默认所有字串都不是回文字串，这样当s[i]！=s[j]时，不如额外进行操作，只需对s[i]=s[j]时，进行操纵即可。
     * 确定dp数组遍历顺序：由于dp[i][j]是根据dp[i+1][j-1]得来，因此需要先得到dp[i+1][j-1]的状态才能算出dp[i][j]的状态，因此不能从上到下从左到右进行遍历，而是应该从下到上从左到右进行遍历。
     * 时间复杂度：O(n^2)，空间复杂度：O(n^2)
     *
     * @param s 字符串s
     * @return s中的回文字符个数
     */
    public int countSubstrings(String s) {
        //初始化dp数组
        boolean[][] dp = new boolean[s.length()][s.length()];

        //记录回文子串的个数
        int count = 0;

        //先从下往上遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            //后从左往右遍历，并且j从i开始遍历，因为根据dp数组的定义得j>=i
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //i==j和i+1=j的情况
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        count++;
                    }
                    else {
                        //i+1<j时，即i和j相隔距离大于1时，dp[i][j]的情况取决于dp[i+1][j-1]的情况
                        dp[i][j] = dp[i + 1][j - 1];
                        if (dp[i][j] == true)
                            count++;
                    }
                }
            }
        }
        return count;
    }


}
