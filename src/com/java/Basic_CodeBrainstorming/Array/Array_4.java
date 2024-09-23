package com.java.Basic_CodeBrainstorming.Array;

import org.junit.Test;

/**
 * ClassName: Array_4
 * Package: com.java.Basic_CodeBrainstorming.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/4 11:21
 * @Version 1.0
 */
public class Array_4 {
    @Test
    public void test() {
        System.out.println(minSubArrayLen2(16, new int[]{2, 3, 1, 2, 4, 3}));
    }

    //法一：暴力枚举法，双循环依次查找符合条件的子序列，找出最小的序列长度，时间复杂度为O(n2)
    public static int minSubArrayLen(int target, int[] nums) {
        int result = nums.length;//符合条件的子序列长度，最长为数组的长度
        int sum;//当前子序列之和
        int sublength;//当前子序列长度
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {//双循环遍历数组，找最小的子序列
            //新一轮查找，归零
            sublength = 0;
            sum = 0;
            for (int j = i; j < nums.length; j++) {//当前轮次查找
                sum += nums[j];
                sublength++;
                if (sum >= target) {//子序列之和>=target
                    flag = true;//存在最小子序列，标志位置为true
                    result = result < sublength ? result : sublength;//判断当前最小子序列的长度是否比前面轮次的长度小，小则替换
                    break;//当前轮次已找到序列，退出当前轮次
                }
            }
        }
        if (flag == true) {//存在最小序列
            return result;
        } else {//不存在
            return 0;
        }
    }


    //法二：滑动窗口:确定滑动窗口的边界
    //窗口就是满足其和 ≥ s 的长度最小的连续子数组。
    //窗口的起始位置如何移动：如果当前窗口的值大于s了，窗口就要向前移动了（也就是该缩小了）。
    //窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，也就是for循环里的索引。
    public static int minSubArrayLen2(int target, int[] nums) {
        int result = nums.length;//符合条件的子序列长度，最长为数组的长度
        int sum = 0;//子序列之和
        int i = 0;//滑动窗口的起始位置
        int sublength = 0;//子序列长度
        boolean flag = false;//标志位，是否存在子序列，存在为true，默认为false
        for (int j = 0; j < nums.length; j++) {//滑动窗口右边界遍历整个数组
            sum += nums[j];//序列求和
            while (sum >= target) {//满足序列和>=目标整数
                flag = true;
                sublength = (j - i) + 1;//计算当前子序列的长度
                result = result > sublength ? sublength : result;//判断当前最小子序列的长度是否比前面轮次的长度小，小则替换
                //重点！
                sum -= nums[i++];//滑动窗口左边界右移一位，序列和减少
            }
        }
        if (flag == true) {//标志位为true，存在子序列
            return result;
        } else {//不存在子序列
            return 0;
        }
    }
}


