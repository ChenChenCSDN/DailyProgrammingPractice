package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_34
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/23 15:05
 * @Version 1.0
 */
public class DynamicProgramming_34 {
    public static void main(String[] args) {
        new Solution34().isSubsequence2("abc", "ahbgdcds");
    }
}

class Solution34 {
    /**
     * 动态规划：判断s是否为t的子序列，即求s和t的最大公共子序列，如果最大公共子序列的长度正好等于s的长度，则s为t的子序列，反之亦然。故本题思路同求最大公共子序列的问题。
     * 此时的dp[i][j]表示长度为0~i(包含)的字符串s和长度为0~j(包含)的字符串t中的公共子序列的长度
     *
     * @param s 字符串s
     * @param t 字符串t
     * @return true：s是t的子序列 false：s不是t的子序列
     */
    public boolean isSubsequence(String s, String t) {
        //边界条件：s为空，一定为t的子序列
        if (s.isEmpty())
            return true;
        if (t.isEmpty())
            return false;
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();


        int[][] dp = new int[s.length()][t.length()];
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == s2[0]) {
                for (int j = i; j < s1.length; j++) {
                    dp[j][0] = 1;
                }
                break;
            }
        }

        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == s1[0]) {
                for (int j = i; j < s2.length; j++) {
                    dp[0][j] = 1;
                }
                break;
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
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //如果最大公共子序列的长度正好等于s的长度，则s为t的子序列
        return dp[s1.length - 1][s2.length - 1] == s1.length;
    }

    /**
     * 动态规划：对于判断子序列问题，t的长度一定大于s，若t的当前元素与s不同，则可以将其删除（不实际删除，只是递推公式要删除），以此来找出公共子序列的长度。
     * 确定dp数组及其下标含义：dp【i】【j】理解为s【0:i】，t【0:j】两个字符串的最长公共子序列的长度，且s【0:i】的子序列必须以s【i-1】结尾
     * 确定dp数组递推公式：f (s[i - 1] == t[j - 1])：t中找到了一个字符在s中也出现了，则dp[i][j] = dp[i-1][j-1]+1，if (s[i - 1] != t[j - 1])：相当于t要删除元素，继续匹配，则dp[i][j] = dp[i][j-1]；
     * 时间复杂度：O(n*m)，空间复杂度：O(n*m)
     *
     * @param s 字符串s
     * @param t 字符串t
     * @return true：s是t的子序列 false：s不是t的子序列
     */
    public boolean isSubsequence2(String s, String t) {
        //边界条件：s为空，一定为t的子序列
        if (s.isEmpty())
            return true;
        if (t.isEmpty())
            return false;
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();

        //初始化dp数组
        int[][] dp = new int[s.length()][t.length()];
        //只初始化s1的第一个元素的情况，因为对于s2的第一个元素与s1的所有元素比较，长度不同（s1不能删元素）
        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == s1[0]) {
                for (int j = i; j < s2.length; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else//当前元素不同，删s2中的元素，则当前状态为不包含s2当前元素的状态
                    dp[i][j] = dp[i][j - 1];
            }
        }
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //如果以s1.length - 1和s2.length - 1结尾的最大公共子序列的长度正好等于s的长度，则s为t的子序列
        return dp[s1.length - 1][s2.length - 1] == s1.length;
    }

    /**
     * 双指针法：分别设置两个指针a和b指向s和t的起点，b开始遍历t，若a指向的元素和b指向的元素相同则a++，否则只让b+1，最后判断a是否移动到s的末尾即可。
     * 时间复杂度：O(n)，空间复杂度：O(1)
     *
     * @param s 字符串s
     * @param t 字符串t
     * @return true：s是t的子序列 false：s不是t的子序列
     */
    public boolean isSubsequence3(String s, String t) {
        //边界条件：s为空，一定为t的子序列
        if (s.isEmpty())
            return true;
        if (t.isEmpty())
            return false;
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        //i指向s
        int i = 0;
        //j指向t
        for (int j = 0; j < s2.length; j++) {
            if (i < s1.length && s1[i] == s2[j])//i与j指向的元素相同，则i++
                i++;
        }

        //i移动到s1的尾部，说明存在子序列
        return i == s1.length;
    }
}
