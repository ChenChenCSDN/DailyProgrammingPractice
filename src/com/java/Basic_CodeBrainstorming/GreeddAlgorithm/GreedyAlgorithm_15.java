package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.sql.Connection;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: GreedyAlgorithm_15
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @Author chen_sir
 * @Create 2024/5/17 10:03
 * @Version 1.0
 */
public class GreedyAlgorithm_15 {
    @Test
    public void test() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{1, 4}, {5, 6}};
        new Solution15().merge(intervals);
    }
}

class Solution15 {
    /*
        思路：将区间按照左边界升序排序，然后判断区间是否重叠，定义start为最小左边界，rightmostRightBoun为最大右边界，若重叠则更新最大右边界，左边界不动；
             若不重叠，将将该区间加入到result数组中，然后更新最小左边界和最大右边界为当前区间的左右边界。
    */
    //时间复杂度: O(nlogn)，快排的时间复杂度
    //空间复杂度: O(logn)，排序需要的空间开销
    public int[][] merge(int[][] intervals) {
        //按照左边界排序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];//前-后：升序；后-前：降序
        });
        //Arrays.sort(intervals, new Comparator<int[]>() {
        //    @Override
        //    public int compare(int[] a, int[] b) {
        //        if (a[0] == b[0]) {
        //            return a[1] - b[1];
        //        } else {
        //            return a[0] - b[0];
        //        }
        //    }
        //});
        //initial start是最小左边界
        List<int[]> result = new ArrayList<>();
        int start = intervals[0][0];
        int rightmostRightBound = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //如果左边界大于最大右边界
            if (intervals[i][0] > rightmostRightBound) {
                //加入区间，更新start
                result.add(new int[]{start, rightmostRightBound});
                start = intervals[i][0];
                rightmostRightBound = intervals[i][1];
            } else {
                //更新最大有边界
                rightmostRightBound = Math.max(rightmostRightBound, intervals[i][1]);
            }
        }
        //将最后一个元素加入result中
        result.add(new int[]{start, rightmostRightBound});
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
        //将list转为二维数组
        return result.toArray(new int[result.size()][]);
    }
}