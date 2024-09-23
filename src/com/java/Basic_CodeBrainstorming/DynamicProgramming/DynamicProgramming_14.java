package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: DynamicProgramming_14
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/9 10:05
 * @Version 1.0
 */
public class DynamicProgramming_14 {
    public static void main(String[] args) {
        new Solution14().combinationSum43(new int[]{1, 2, 3}, 4);
    }
}

class Solution14 {

    /**
     * 动态规划：此题能够无限次选取物品，故属于完全背包问题，但区别于目标和问题，此题是要求排列数{（1，2）和（2，1）是不同的排列方式}，而不是组合数{（1，2）和（2，1）是相同的组合方式}
     * 因此相比于完全背包问题，如果先遍历物品，后遍历背包容量，会导致物品是从0~i依次选取的，不会存在先取物品2，后取物品1的情况，因此就不会出现（2，1）这个排序方式，
     * 故需要先遍历背包容量，后遍历物品，因为先遍历容量时，会把所有物品都遍历一遍，再次遍历容量会再次遍历所有物品，故会出现（2，1）的情况
     * 也可以这么理解：先遍历物品的时候相当于是先把这个物品放进去了然后再看其他的能不能放进去，所以不会出现逆序，先遍历背包相当于是用每个大小的背包看看把每一个物品都放进去一次再看别的物品能不能放进去，所以可以有逆序
     * 同事此题为求组合数问题，故递推公式是：dp[j] = dp[j] + dp[j-weight[i]]
     * 注：此题很难使用二维数组，因此二维数组无法实现记录之前放入的情况
     * 时间复杂度：O(m*n)，空间复杂度：空间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum43(int[] nums, int target) {
        //定义dp数组并初始化
        int[] dp = new int[target + 1];
        dp[0] = 1;
        //必须先遍历背包容量，后遍历物品
        for (int i = 0; i < target + 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j])
                    dp[i] = dp[i] + dp[i - nums[j]];
            }
        }

        //打印dp数组，验证是否满足预期
        System.out.println(Arrays.toString(dp));

        return dp[target];
    }

    /**
     * 回溯法：求数字的不同组合，顺序不同的序列被视作不同的组合，很容易可以想到使用回溯法，记录每种情况。
     * 需注意的是顺序不同的序列被视作不同的组合，因此每次都可以从i=0开始遍历，无需记录startIndex
     * 时间复杂度：O(n*2^n)，空间复杂度：空间复杂度：O(target)
     *
     * @param nums   不同数字组成的数组
     * @param target 目标整数
     * @return 不同序列的组合数
     */
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        BackTrace(nums, target, 0);
        result.forEach(list -> System.out.println(list.toString()));
        return result.size();
    }

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    public void BackTrace(int[] nums, int target, int sum) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length && sum + nums[i] <= target; i++) {
            sum += nums[i];
            path.add(nums[i]);
            BackTrace(nums, target, sum);
            sum -= nums[i];
            path.remove(path.size() - 1);
        }
    }
}
