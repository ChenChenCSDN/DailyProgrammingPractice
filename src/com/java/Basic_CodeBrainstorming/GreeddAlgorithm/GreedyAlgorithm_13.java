package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: GreedyAlgorithm_13
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * <p>
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * @Author chen_sir
 * @Create 2024/5/15 10:03
 * @Version 1.0
 */
public class GreedyAlgorithm_13 {
    @Test
    public void test() {
        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(new Solution13().eraseOverlapIntervals(intervals));
    }
}

class Solution13 {
    //思路1(直接法)：与上一道射箭题目思路相同，先将区间升序排序，然后判断是否相交，若相交则记录次数，并更新最小的右边界
    //时间复杂度：O(nlogn)，因为要快排
    //空间复杂度：O(1)~O(n)，有一个快排，最好情况为O(1)，最差情况倒序，需要n次递归调用，需要O(n)的栈空间
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];//第一个元素相同，则按照第二个元素升序排序
            return a[0] - b[0];
        });//a[i]-b[i]>0说明a[i]大，让a[i]排在后面;a[i]-b[i]<0说明b[i]大，让a[b]排在后面;升序排序
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                count++;
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);//更新取最小右边界
            }
        }
        return count;
    }

    //思路2(间接法)：采用射箭的思路，算出所需要射箭的数目（不重叠区间个数），然后用总个数-所需箭数即为所要删除的重叠区间个数
    public int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];//第一个元素相同，则按照第二个元素升序排序
            return a[0] - b[0];
        });//a[i]-b[i]>0说明a[i]大，让a[i]排在后面;a[i]-b[i]<0说明b[i]大，让a[b]排在后面;升序排序

        //区间不为空时，至少需要一支箭
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[i - 1][1]) {//不重叠，箭个数加一
                count++;
            } else {
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
            }
        }
        return intervals.length - count;
    }
}

