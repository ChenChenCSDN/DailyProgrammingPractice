package com.java.Basic_CodeBrainstorming.StackAndQueue;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import org.junit.Test;

import java.util.*;

/**
 * ClassName: StackAndQueue_6
 * Package: com.java.Basic_CodeBrainstorming.StackAndQueue
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/25 15:26
 * @Version 1.0
 */
public class StackAndQueue_6 {
    @Test
    public void test() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        Solution6.maxSlidingWindow(nums, 8);
        Solution6 solution6 = new Solution6();
        solution6.maxSlidingWindow3(nums, 3);
    }
}

class Solution6 {
    //法一：暴力方法，对当前范围内的数组查找最大值，然后移动边界，继续查找，直至全部查找完毕，但时间复杂度过高，为O(n*k)
    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            while (j < k - 1) {
                j++;
            }
            int[] temp = Arrays.copyOfRange(nums, i, j + 1);
            int max = mathNumber(temp);
            list.add(max);
            i++;
        }
        int[] result = new int[list.size()];
        for (i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static int mathNumber(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            if (num > max)
                max = num;
        }
        return max;
    }

    //法二：自定义构建单调队列，单调队列的队头即为最大值
    class MyQueue {
        Deque<Integer> queue = new LinkedList<>();

        // 如果push的数值大于入口元素的数值，那么就将队列后端的数值弹出，直到push的数值小于等于队列入口元素的数值为止。
        // 这样就保持了队列里的数值是单调从大到小的了。
        public void add(int val) {
            while (!queue.isEmpty() && val > queue.peekLast())
                queue.pollLast();
            queue.offerLast(val);
        }

        //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
        //同时判断队列当前是否为空，如1，3，-1遍历完后队列存放的是3，不应该把3出队
        public void pop(int val) {
            if (!queue.isEmpty() && val == queue.peekFirst())
                queue.pollFirst();
        }

        //查询当前队列里的最大值 直接返回队列前端的值即可
        public int getMax() {
            return queue.peekFirst();
        }
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];//存放结果元素的数组
        //自定义队列
        MyQueue myQueue = new MyQueue();
        int index = 0;
        //先将前k的元素放入队列
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        result[index++] = myQueue.getMax();
        for (int i = k; i < nums.length; i++) {
            //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            myQueue.pop(nums[i - k]);
            //滑动窗口加入最后面的元素
            myQueue.add(nums[i]);
            //记录对应的最大值
            result[index++] = myQueue.getMax();
        }
        return result;
    }

    //法三：原理同法二，区别在于该方法手动模拟单调队列
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];//存放结果元素的数组
        int index = 0;
        //自定义队列
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            // 如果push的数值大于入口元素的数值，那么就将队列后端的数值弹出，直到push的数值小于等于队列入口元素的数值为止。
            // 这样就保持了队列里的数值是单调从大到小的了。
            while (!queue.isEmpty() && queue.peekLast() < nums[i])
                queue.pollLast();
            queue.offerLast(nums[i]);
        }
        result[index++] = queue.peekFirst();
        for (int i = k; i < nums.length; i++) {
            //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
            //同时判断队列当前是否为空，如1，3，-1遍历完后队列存放的是3，不应该把3出队
            if (nums[i - k] == queue.peekFirst())
                queue.pollFirst();
            // 如果push的数值大于入口元素的数值，那么就将队列后端的数值弹出，直到push的数值小于等于队列入口元素的数值为止。
            // 这样就保持了队列里的数值是单调从大到小的了。
            while (!queue.isEmpty() && queue.peekLast() < nums[i])
                queue.pollLast();
            queue.offerLast(nums[i]);
            result[index++] = queue.peekFirst();
        }
        return result;
    }
}
