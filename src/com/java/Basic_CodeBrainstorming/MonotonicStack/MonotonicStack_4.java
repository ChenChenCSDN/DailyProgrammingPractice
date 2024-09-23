package com.java.Basic_CodeBrainstorming.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ClassName: MonotonicStack_4
 * Package: com.java.Basic_CodeBrainstorming.MonotonicStack
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/9/9 10:35
 * @Version 1.0
 */
public class MonotonicStack_4 {
    public static void main(String[] args) {
        System.out.println(new Solution4().trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}

class Solution4 {
    /**
     * 法一：双指针，按照列进行计算，宽度为1，只需求出每一列的高度即可，而每一列雨水的高度，取决于，该列左侧最高的柱子和右侧最高的柱子中最矮的那个柱子的高度，然后减去自身的高度就是所能接的水的容量。
     * 时间复杂度：O(n^2)，空间复杂度：O(1)
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            // 第一个柱子和最后一个柱子不接雨水
            if (i == 0 || i == height.length - 1)
                continue;
            int rHeight = height[i];// 记录右边柱子的最高高度
            int lHeight = height[i];// 记录左边柱子的最高高度

            //查找当前列右边最高的柱子高度
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > rHeight) rHeight = height[j];
            }

            //查找当前列左边最高的柱子高度
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > lHeight) lHeight = height[j];
            }

            //计算当前列能接的水滴容量
            int temp = Math.min(rHeight, lHeight) - height[i];
            if (temp > 0)
                sum += temp;
        }
        return sum;
    }

    /**
     * 法二：单调栈，本题求雨水的容量就是要求当前柱子左边第一个高的元素位置和右边第一个高的元素位置，然后高度为min(左边高的柱子高度，右边高的柱子高度），长度为下标之差。
     * 注：使用单调栈来求解是横向来求雨水的容量。
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int sum = 0;

        //单调栈：存储元素下标，递增
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int i = 1; i < height.length; i++) {
            //当前元素小于等于栈顶元素，入栈
            if (height[i] < height[stack.peek()])
                stack.push(i);
            else if (height[i] == height[stack.peek()]) {
                //因为相等的相邻墙，左边一个是不可能存放雨水的，所以pop左边的i，存入当前di
                stack.pop();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    //记录当前柱子的高度
                    int mid = height[stack.peek()];
                    stack.pop();
                    if (!stack.isEmpty()) {
                        //雨水高度是 min(凹槽左边高度, 凹槽右边高度) - 凹槽底部高度
                        int h = Math.min(height[stack.peek()], height[i]) - mid;
                        //雨水的宽度是 凹槽右边的下标 - 凹槽左边的下标 - 1（因为只求中间宽度）
                        int w = i - stack.peek() - 1;
                        //凹槽雨水的体积就是：h * w
                        int hold = h * w;
                        if (hold > 0)
                            sum += hold;
                    }
                }
                stack.push(i);
            }
        }
        return sum;
    }
}
