package com.java.Basic_CodeBrainstorming.MonotonicStack;

import javax.print.DocFlavor;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * ClassName: MonotonicStack_3
 * Package: com.java.Basic_CodeBrainstorming.MonotonicStack
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/9/9 9:03
 * @Version 1.0
 */
public class MonotonicStack_3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution3().nextGreaterElements2(new int[]{1, 2, 3, 4, 3})));
    }
}

class Solution3 {
    /**
     * 单调栈：本题与之前的每日温度和下一个更大元素思路相同，不过本题的数组是循环数组，因此对于元素的遍历要进行循环处理。有两种思路：一是将两个数组拼起来，组合成循环数组，根据循环数组构建单调栈来查找元素，缺点是需要额外创建数组；二是模拟循环遍历数组，每次遍历时根据i%nums.length来确定循环数组的位置。
     * 时间复杂度：O(2n)，空间复杂度：O(n)
     *
     * @param nums 待查找到数组
     * @return 结果数组
     */
    public int[] nextGreaterElements(int[] nums) {
        //将两个数组拼起来，组合成循环数组
        int[] curNums = new int[nums.length * 2 - 1];
        for (int i = 0; i < curNums.length; i++) {
            curNums[i] = nums[i % nums.length];
        }

        //结果数组，初始化为-1
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        //单调栈：存储元素下标，递增
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int i = 1; i < curNums.length; i++) {
            //当前元素小于等于栈顶元素，入栈
            if (curNums[i] <= curNums[stack.peek()])
                stack.push(i);
            else {
                while (!stack.isEmpty() && curNums[i] > curNums[stack.peek()]) {
                    result[stack.peek() % nums.length] = curNums[i % nums.length];
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return result;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 0; i < nums.length * 2; i++) {
            if (nums[i % nums.length] <= nums[stack.peek() % nums.length])
                stack.push(i);
            else {
                while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek() % nums.length]) {
                    result[stack.peek() % nums.length] = nums[i % nums.length];
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return result;
    }
}
