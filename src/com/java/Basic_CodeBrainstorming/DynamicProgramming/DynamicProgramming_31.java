package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_31
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/21 15:48
 * @Version 1.0
 */
public class DynamicProgramming_31 {
    public static void main(String[] args) {
        new Solution31().longestCommonSubsequence("abcde", "cde");
    }
}

class Solution31 {
    /**
     * 动态规划：本题区分于求最长重复子数组问题，最长重复子数组问题要求子数组连续，因此只对nums[i]=nums[j]的情况下进行处理，不相同则说明不重复且不连续，而本题不要求是连续的，因此不能只判断nums[i]=nums2[j]的情况，还要对nums[i]！=nums2[j]的情况进行考虑
     * 比如：“abcde”和“ced”的情况，当i为d，j为e时，nums[i]！=nums2[j]但公共子序列长度为1
     * 确定dp数组及其下标含义：dp[i][j]表示长度为0~i(包含)的字符串s1和长度为0~j(包含)的字符串s2中的公共子序列的长度
     * 确定dp数组递推公式：由于公共子序列可以不连续，因此即使对于nums[i]！=nums2[j]的情况也要进行考虑。对于nums[i]=nums2[j]的情况，易知dp[i][j] = dp[i-1][j-1] + 1，而对于nums[i]！=nums2[j]的情况，需要比较不包含i和j的元素时s1和s2中的哪个公共子序列长度最大，
     * 故dp[i][j] = max{dp[i-1][j],dp[i][j-1]}
     * 确定dp数组初始化：将s1的第一个元素和所有的s2中的元素进行比较，若相同则后面所有的元素均赋值为1，因为abced和a相比，abc和a的公共子序列长度也为1，因为之前s1中的a等于s2中的a，同理s2的第一个元素和所有s1中的元素进行比较，进行赋值。
     * 其中对于dp[i-1][j]，相当于当nums[i]！=nums2[j]时，将nums[i]这个元素删掉，然后再进行比较。dp[i][j-1]同时，是删nums2[j]
     * 确定dp数组遍历顺序：s1和s2遍历顺序无要求
     * 时间复杂度：O(n^2)，空间复杂度：O(n^2)
     *
     * @param text1 字符串text1
     * @param text2 字符串text2
     * @return 两个字符串的最长公共子序列长度
     */
    public int longestCommonSubsequence(String text1, String text2) {
        //将字符串转为字符数组，便于操作
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();

        int[][] dp = new int[s1.length][s2.length];

        //初始化dp数组
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == s2[0]) {//元素相同，则后续所有子字符串的公共序列长度均为1
                for (int j = i; j < s1.length; j++) {
                    dp[j][0] = 1;
                }
                break;
            }
        }

        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == s1[0]) {//元素相同，则后续所有子字符串的公共序列长度均为1
                for (int j = i; j < s2.length; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }

        //遍历dp数组
        //即可先遍历s1也可先遍历s2，但是由于初始化时先初始化的s1
        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        //打印dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //之所以dp[s1.length - 1][s2.length - 1]是最大值因为其字符串末尾的结果是由之前的结果得来的，包含了之前的情况，故为最大值
        return dp[s1.length - 1][s2.length - 1];
    }
}
