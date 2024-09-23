package com.java.Basic_CodeBrainstorming.DoublePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: DoublePoint
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/21 15:58
 * @Version 1.0
 */
public class DoublePoint_10 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] > target)
                return result;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int j = nums.length - 1;
            while ((i + 3) <= j) {
                int left = i + 1;
                int right = j - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right] + nums[j];
                    if (sum > target)
                        right--;
                    else if (sum < target)
                        left++;
                    else {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right], nums[j]));
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        right--;
                        left++;
                    }
                }
                while ((i + 3) <= j && nums[j] == nums[j - 1]) j--;
                j--;
            }
        }
        return result;
    }
}
