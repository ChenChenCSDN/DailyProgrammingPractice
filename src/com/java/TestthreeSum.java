package com.java;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: practice
 * Package: com.java
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/28 9:59
 * @Version 1.0
 */
public class TestthreeSum{
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>0)
                return result;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> path=new ArrayList<>();
                    path.add(nums[i]);
                    path.add(nums[j]);
                    path.add(nums[k]);
                    result.add(path);
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    j++;
                    k--;
                } else if (sum > 0)
                    k--;
                else
                    j++;
            }
        }
        return result;
    }
}












