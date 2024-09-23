package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_35
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/23 17:00
 * @Version 1.0
 */
public class DynamicProgramming_35 {
    public static void main(String[] args) {
        new Solution35().numDistinct("bagbgbag", "bag");
    }
}

class Solution35 {
    /**
     * 动态规划：本题与上一题的判断子序列个数问题的核心思想一致，都是求子序列问题，但是上一题是要求s1是否是s2的子序列，而本题是求s1能构成s2中的多少个子序列，故是求个数问题。
     * 确定dp数组及其下标定义：dp【i】【j】理解为由t【0:i】构成s【0:j】的子序列的个数，且t【0:i】的子序列必须以t【i-1】结尾，s【0:j】的子序列不一定以s【j-1】结尾
     * 确定dp数组递推公式：对于s1[i]!=s2[j]的情况很好判断，即删除s2的元素，以不包含s2当前元素的状态进行比较：dp[i][j] = dp[i][j-1]，而对于s1[i]=s2[j]的情况，由于可以重复构成，故分为两种情况，一个是使用当前s2的元素能构成的方法数，另一个是不使用当前s2的元素能构成的方法数。
     * 对于使用当前s2的元素能构成的方法数：dp[i][j] = dp[i-1][j-1]，不使用当前s2的元素能构成的方法数：dp[i][j] = dp[i][i-1]，至于为什么对于不使用当前s2的元素的情况只删去了s2的元素即j-1，是因为解题思路是求s1来构成s2的方法数，故s1不能删。
     * 时间复杂度：O(n*m)，空间复杂度：O(n*m)
     *
     * @param s 字符串s
     * @param t 字符串t
     * @return t能够成s的方法数
     */
    public int numDistinct(String s, String t) {
        //注意：本题的dp含义是t构成s的个数，因此初始化时必须遍历s2（对应字符串s）
        char[] s1 = t.toCharArray();
        char[] s2 = s.toCharArray();

        int[][] dp = new int[t.length()][s.length()];

        //记录s1[0]与s2中有多少个相同的情况，初始为0
        int count = 0;
        for (int i = 0; i < s2.length; i++) {
            if (s1[0] == s2[i]) {//元素相同，相同情况的数量+1
                dp[0][i] = ++count;
            } else
                dp[0][i] = count;
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j])//当前元素相同，则当前构成的方法数：s1和s2都不使用相同元素时的方法数+s2不使用相同元素的方法数，因为s2不使用当前元素，之前的子序列仍然可能有与s1的子序列相同的情况。
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else//当前元素不相同，则删除s2中的元素，与不包含s2当前元素的状态进行比较
                    dp[i][j] = dp[i][j - 1];
            }
        }
        //dp[s1.length - 1][s2.length - 1]表示s1从0到s1.length - 1范围内（一定以最后一个元素结尾）的元素能构成s2从0到s1.length - 1范围内（不一定以最后一个元素结尾）的元素子序列的个数
        return dp[s1.length - 1][s2.length - 1];
    }
}
