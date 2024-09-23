package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_36
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/24 18:20
 * @Version 1.0
 */
public class DynamicProgramming_36 {
    public static void main(String[] args) {
        new Solution36().minDistance("etco", "leetcode");
    }
}

class Solution36 {
    /**
     * 动态规划：本题是求字符串s1和字符串s2同时删去字符串中的字符，使得剩下的字符串相等，所要删除的最小字符个数。上一题是求s1构成s2的个数，则对于不相同的情况，只能删除s2，不能删除s1，故dp[i][j] = dp[i][j-1]，而本题是s1和s2均可删除字符，故dp[i][j] = {dp[i-1][j],dp[i][j-1]}。
     * 确定dp数组及其下标含义：dp[i][j]表示以从0开始到i为结尾(包括)的字符串s1，和以从0开始到j位结尾(包括)的字符串s2，想要达到相等，所需要删除元素的最少次数。
     * 确定dp数组递推公式：当s1[i]=s2[j]时表明元素相同，不需要删除元素，故dp[i][j] = dp[i-1][j-1]，而当s1[i]！=s2[j]时表明元素不相同，不相同则删除元素有三种思路：1.删除s1的元素，2.删除s2的元素，3.s1和s2的元素均删除，其中分别对应dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+2，
     * 又因为是求最少删除元素的个数，故dp[i][j] = min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+2)
     * 确定dp数组初始化：由于从0开始算第一个元素，故要初始化所有s1[0]和s2[0]对应的情况，具体情况可画dp数组进行分析。
     * 时间复杂度：O(n*m)，空间复杂度：O(n*m)
     *
     * @param word1 字符串s1
     * @param word2 字符串s2
     * @return 使得s1和s2相同所要删除的最小字符个数
     */
    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();

        int[][] dp = new int[s1.length][s2.length];

        //记录与s1[0]和s2[0]相同元素的个数
        int count = 0;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == s2[0]) {//当前元素相同，从0到i共有i+1个元素，相同则当前元素不删，故需删i+1-count个元素
                count = 1;
                dp[i][0] = i + 1 - count;
            } else//当前元素不同，从0到i共有i+1个元素，不相同则当前元素也删，则需要删除i+1+1=i+2个元素，又因为需要考虑之前是否有相同元素的情况，若有则保留两个相同的元素，故需删除i+2-count*2
                dp[i][0] = i + 2 - count * 2;
        }

        //归零
        count = 0;
        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == s1[0]) {//当前元素相同，从0到i共有i+1个元素，相同则当前元素不删，故需删i+1-count个元素
                count = 1;
                dp[0][i] = i + 1 - count;
            } else//当前元素不同，从0到i共有i+1个元素，不相同则当前元素也删，则需要删除i+1+1=i+2个元素，又因为需要考虑之前是否有相同元素的情况，若有则保留两个相同的元素，故需删除i+2-count*2
                dp[0][i] = i + 2 - count * 2;
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j]) {//当前元素相同，则都不删，取二者之前不含该元素的状态
                    dp[i][j] = dp[i - 1][j - 1];
                } else {//当前元素不同，取删s1的元素、删s2的元素和同时删s1和s2元素的最小值
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        //dp[s1.length - 1][s2.length - 1]表示从0到s1结尾(包含)的字符串和从0到s2结尾(包含)的字符串相同所要删除元素的个数
        return dp[s1.length - 1][s2.length - 1];
    }

    /**
     * 动态规划法二：记录s1和s2的最长公共子序列的长度，然后将s1和s2的总长度之和减去2*最长公共子序列的长度即可。dp数组含义及初始化过程同求最长公共子序列问题的思路。
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance3(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();

        int[][] dp = new int[s1.length][s2.length];
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == s2[0]) {
                for (int j = i; j < s1.length; j++) {
                    dp[j][0] = 1;
                }
            }
        }

        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == s1[0]) {
                for (int j = i; j < s2.length; j++) {
                    dp[0][j] = 1;
                }
            }
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return s1.length + s2.length - dp[s1.length - 1][s2.length - 1] * 2;
    }
}
