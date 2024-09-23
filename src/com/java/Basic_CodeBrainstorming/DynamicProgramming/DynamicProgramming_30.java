package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_30
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/20 11:05
 * @Version 1.0
 */
public class DynamicProgramming_30 {
    public static void main(String[] args) {
//        new Solution30().findLength2(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
        new Solution30().findLength2(new int[]{1, 2, 3, 2, 8}, new int[]{5, 6, 1, 4, 7});
    }
}

class Solution30 {
    /**
     * 暴力解法：先用两层for循环确定两个数组的起始位置，然后内部再用一个while循环来控制从起始位置开始比较，记录最长公共子数组的长度
     * 时间复杂度：O(n^3)，空间复杂度：O(1)
     *
     * @param nums1 待查找的数组1
     * @param nums2 待查找的数组2
     * @return 最长公共子数组的长度
     */
    public int findLength(int[] nums1, int[] nums2) {
        //记录最长公共子数组的长度
        int result = 0;
        //两层for循环控制两个数组的起始位置
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                //k用于记录相同子数组的长度
                int k = 0;
                //while循环用于控制从起始位置开始比较公共子数组
                while (i + k < nums1.length && j + k < nums2.length && nums1[i + k] == nums2[j + k])
                    k++;
                //记录最长公共子数组的长度
                result = Math.max(result, k);
            }
        }
        return result;
    }

    /**
     * 动态规划：
     * 确定dp数组及其下标含义：dp[i][j]表示下标为i(包含)结尾的A和下标为j(包括)结尾的B的公共子序列的长度，是以i和j结尾的序列但是起始元素不一定是从0开始
     * 确定dp数组递推公式：如果nums[i]=nums[j]，那么dp[i][j] = dp[i-1][j-1] + 1；因为如果nums[i]=nums[j]的话，说明两数组中的当前元素相同，则公共子序列长度为不包含当前元素的子序列长度 + 1，如果nums[i]！=nums[j]说明不是相同的元素，不满足公共子序列，故不计算
     * 确定dp数组初始化：由dp[i][j] = dp[i-1][j-1] + 1可知，i和j必须从1开始，那么如果nums1[i] 与 nums2[0] 相同的话，对应的 dp[i][0]就要初始为1，因为此时最长重复子数组为1。 nums2[j] 与 nums1[0]相同的话，同理。并且如果存在相同的情况下，那么最小公共子序列的长度一定大于等于1
     * 需要记录下来result=1，因为在两层for循环中i和j从1开始，此时result记录的是i和j都从1开始时最长公共子序列的长度，但对于nums1=1, 2, 3, 2, 8和nums2=5, 6, 1, 4, 7的情况，从1开始时不存在公共子序列，故需要提前将resul赋值为1
     * 确定dp数组遍历顺序：即可先遍历nums1，也可先遍历nums2。
     * 时间复杂度：O(n^2)，空间复杂度：O(n^2)
     *
     * @param nums1 待查找的数组1
     * @param nums2 待查找的数组2
     * @return 最长公共子数组的长度
     */
    public int findLength2(int[] nums1, int[] nums2) {
        //记录最长公共子数组的长度
        int result = 0;
        int[][] dp = new int[nums1.length][nums2.length];

        //初始化dp数组
        for (int i = 0; i < nums1.length; i++) {
            //nums2第一个元素与nums1中的元素有相同的情况，则最长公共子序列长度一定大于等于1
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                result = 1;
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            //nums1第一个元素与nums2中的元素有相同的情况，则最长公共子序列长度一定大于等于1
            if (nums2[i] == nums1[0]) {
                dp[0][i] = 1;
                result = 1;
            }
        }

        //遍历dp数组
        //即可先遍历nums1也可先遍历nums2
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                //记录度最大公共子序列数组的长
                result = Math.max(result, dp[i][j]);
            }
        }
        
        //打印dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));
        return result;
    }
}
