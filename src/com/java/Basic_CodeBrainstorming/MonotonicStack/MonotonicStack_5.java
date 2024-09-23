package com.java.Basic_CodeBrainstorming.MonotonicStack;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * ClassName: MonotonicStack_5
 * Package: com.java.Basic_CodeBrainstorming.MonotonicStack
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/9/11 10:43
 * @Version 1.0
 */
public class MonotonicStack_5 {
    public static void main(String[] args) {
        System.out.println(new Solution5().largestRectangleArea2(new int[]{2, 1, 5, 6, 2, 3}));
    }
}

class Solution5 {
    /**
     * 暴力求解法：矩形的面积由长和宽组成，
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;
            //记录右边第一个小于当前元素的位置
            for (; right < heights.length; right++) {
                if (heights[right] < heights[i]) break;
            }

            //记录左边第一个小于当前元素的位置
            for (; left >= 0; left--) {
                if (heights[left] < heights[i]) break;
            }

            //计算面积：
            //长为右边第一个小于当前元素的位置 - 左边第一个小于当前元素的位置 - 1
            int h = right - left - 1;
            //高为当前元素的值
            int w = heights[i];
            //取最大面积值
            result = Math.max(result, w * h);
        }
        return result;
    }

    public int largestRectangleArea2(int[] heights) {
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }

        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < newHeights.length; i++) {
            if (newHeights[i] > newHeights[stack.peek()])
                stack.push(i);
            else if (newHeights[i] > newHeights[stack.peek()]) {
                stack.pop();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                    int middle = newHeights[stack.peek()];
                    stack.pop();
                    if (!stack.isEmpty()) {
                        int left = stack.peek();
                        int right = i;
                        int temp = (right - left - 1) * middle;
                        if (temp > 0)
                            result = Math.max(result,temp);
                    }
                }
                stack.push(i);
            }
        }
        return result;
    }
}
