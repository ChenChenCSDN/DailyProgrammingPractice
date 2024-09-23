package com.java.Basic_CodeBrainstorming.DoublePoint;

import org.junit.Test;

/**
 * ClassName: DoublePoint_1
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/19 10:05
 * @Version 1.0
 */
public class DoublePoint_1 {
    @Test
    public void test() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(nums, 2));
    }


    //双指针的方法（快慢指针法）：i和j指向起始位置，若当前元素为所要移除元素，则i移动，j不同；若当前元素不为所要移除元素，i和j同时移动，同时根据下标更新数组元素
    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val)//当前元素不为所要移除的元素，i和j同时移动，根据下标更新数组元素
                nums[slowIndex++] = nums[fastIndex];
        }
        return slowIndex;
    }
}
