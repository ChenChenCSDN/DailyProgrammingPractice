package com.java.Basic_CodeBrainstorming.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

/**
 * ClassName: MonotonicStack_1
 * Package: com.java.Basic_CodeBrainstorming.MonotonicStack
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/9/8 10:18
 * @Version 1.0
 */
public class MonotonicStack_1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution1().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }
}


class Solution1 {
    /**
     * 单调栈：本题的题意是求右边第一个比当前元素大的值的位置，因此可以使用单调栈进行求解。适合使用单调栈进行求解的题目类型：求左边或者右边第一个大于或小于当前元素的值，这类题目都可以使用单调栈进行求解，区别在于单调栈是递增还是递减的。
     * 另外本题是要求元素之间的间隔距离，因此单调栈存的是元素下标，当前元素如果大于栈顶元素的值，则二者之间的距离为：i-stack.top。其次本题是求比当前元素大的值，因此单调栈需要是递增的。
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param temperatures 温度数组
     * @return 结果数组
     */
    public int[] dailyTemperatures(int[] temperatures) {
        //结果数组，默认为0
        int[] result = new int[temperatures.length];

        //单调栈：存储元素的下标，递增
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        //从第二个元素开始进行比较
        for (int i = 1; i < temperatures.length; i++) {
            //当前元素小于栈顶元素，入栈
            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                //当前元素大于栈顶元素，栈顶元素出栈，直至栈为空或者第一个大于当前元素的栈内元素为止
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    result[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                //当前元素入栈
                stack.push(i);
            }
        }
        //返回结果数组
        return result;
    }
}
