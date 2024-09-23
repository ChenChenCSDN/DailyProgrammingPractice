package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_32
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/22 19:13
 * @Version 1.0
 */
public class DynamicProgramming_32 {
    public static void main(String[] args) {

    }
}

class Solution32 {
    /**
     * 动态规划：直线不能相交，这就是说明在字符串A中 找到一个与字符串B相同的子序列，且这个子序列不能改变相对顺序，只要相对顺序不改变，链接相同数字的直线就不会相交。如：A = [1,4,2]，B = [1,2,4]，公共子序列为：1，4或1，2，二者都是不相交的连线情况，
     * 故本题同上一题中的查找最长公共子序列长度，公共子序列的长度就是不相交连线的个数。具体分析结果见上一题。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            if (nums2[0] == nums1[i]) {
                for (int j = i; j < nums1.length; j++) {
                    dp[j][0] = 1;
                }
                break;
            }
        }

        for (int i = 0; i < nums2.length; i++) {
            if (nums1[0] == nums2[i]) {
                for (int j = i; j < nums2.length; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return dp[nums1.length-1][nums2.length-1];
    }
}
