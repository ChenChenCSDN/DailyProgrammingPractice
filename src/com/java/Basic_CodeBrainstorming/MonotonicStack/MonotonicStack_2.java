package com.java.Basic_CodeBrainstorming.MonotonicStack;

import javax.print.DocFlavor;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

/**
 * ClassName: MonotonicStack_2
 * Package: com.java.Basic_CodeBrainstorming.MonotonicStack
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/9/8 11:02
 * @Version 1.0
 */
public class MonotonicStack_2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4})));
    }
}

class Solution2 {
    /**
     * 单调栈：本题的题意是说：nums1 是 nums2的子集，找nums1中的元素在nums2中下一个比当前元素大的元素，由于是找第一个比当前元素大的值，因此可以使用单调栈，同时nums1是nums2的子集，因此直接遍历nums2的元素即可，判断该元素是否存在于nums1中，由于没有重复元素，可以使用哈希表来记录存储情况。
     * 本题与上一题的每日温度的不同在于，上一题是求元素之间的距离差值，因此单调栈存储的元素是下标，而本题是求第一个值大的元素，是求元素本身，故单调栈存储的是元素值本身。
     * 时间复杂度：O(max{n,m})，空间复杂度：O(n)
     *
     * @param nums1 子集
     * @param nums2 子集所在的数组
     * @return 结果数组
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //结果数组，初始化为-1
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);

        //单调栈：存储元素自身的值，递增
        Deque<Integer> stack = new ArrayDeque<>();

        //哈希表：存储nums1中元素及其下标的对应关系
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], i);
        }

        //初始化单调栈
        stack.push(nums2[0]);

        //遍历nums2数组
        for (int i = 1; i < nums2.length; i++) {
            //当前元素小于等于栈顶元素，入栈
            if (nums2[i] <= stack.peek()) {
                stack.push(nums2[i]);
            } else {
                //当前元素大于栈顶元素
                while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                    //栈顶元素存在于nums1数组中，则根据哈希表找出其在nums1中的存放位置，放入当前元素
                    if (hashMap.get(stack.peek()) != null)
                        result[hashMap.get(stack.peek())] = nums2[i];
                    //当前元素处理完毕，出栈
                    stack.pop();
                }
                //将大于当前元素的值入栈
                stack.push(nums2[i]);
            }
        }
        return result;
    }
}
