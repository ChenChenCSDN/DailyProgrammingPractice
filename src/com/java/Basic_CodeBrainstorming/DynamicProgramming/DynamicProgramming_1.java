package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: Fibonacci
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/7/28 10:06
 * @Version 1.0
 */
public class DynamicProgramming_1 {
    public static void main(String[] args) {
        int result = new Solution1().fib2(3);
        System.out.println(result);
    }
}

class Solution1 {
    /**
     * 法一：递归，根据通式进行递归，时间复杂度：O(2^n) 空间复杂度：O(n)
     *
     * @param: n
     * @return: int 第i个数的斐波那契数值
     **/
    public int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 法二：动态规划，时间复杂度：O(n) 空间复杂度：O(n)
     *
     * @param: n
     * @return: int
     **/
    public int fib2(int n) {
        if (n <= 1)
            return n;
        //dp数组：dp[i]:第i个数的斐波那契数值
        int[] dp = new int[n + 1];
        //dp数组初始化
        dp[0] = 0;
        dp[1] = 1;
        //确定dp数组遍历顺序：从前往后遍历
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}
