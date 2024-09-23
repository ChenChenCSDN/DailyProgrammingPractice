package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: GreedyAlgorithm_12
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * <p>
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 *
 * @Author chen_sir
 * @Create 2024/5/14 10:35
 * @Version 1.0
 */
public class GreedyAlgorithm_12 {
    @Test
    public void test() {
        int[][] points = {{1,2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(new Solution12().findMinArrowShots(points));
    }
}

class Solution12 {
    //思路：要使所用箭数最小，就要射穿更多的重叠气球，因此需要将数组按直径从小到大排序，让气球尽可能的重叠，若重叠则更新右边界位置
    //时间复杂度：O(nlogn)，因为要快排
    //空间复杂度：O(1)~O(n)，有一个快排，最好情况为O(1)，最差情况倒序，需要n次递归调用，需要O(n)的栈空间
    public int findMinArrowShots(int[][] points) {
        // 根据气球直径的开始坐标从小到大进行排序
        // 使用Integer内置比较方法，不会溢出
        Arrays.sort(points, (p1, p2) -> p1[1] < p2[1] ? -1 : 1);

//        Arrays.sort(points, (p1, p2) -> {
//            if(p1[0]==p2[0]) return p1[1]>p2[1]?1:-1;
//            else return p1[0]>p2[0]?1:-1;
//        });
        int count = 1;//points不为空至少需要一支箭
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {//气球i和气球i-1不挨着，注意因为满足xstart ≤ x ≤ xend就只用一支箭，因此这里不是>=
                count++;
            }else{//气球i和气球i-1挨着
                points[i][1] = Math.min(points[i][1],points[i-1][1]);//更新重叠气球最小右边界
            }
        }
        return count;
    }
}
