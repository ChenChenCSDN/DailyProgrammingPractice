package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * ClassName: GreedyAlgorithm_11
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 *
 * @Author chen_sir
 * @Create 2024/5/13 10:51
 * @Version 1.0
 */
public class GreedyAlgorithm_11 {
    @Test
    public void test() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] result = new Solution11().reconstructQueue(people);
        for (int[] person : result) {
            System.out.println(Arrays.toString(person));
        }
    }
}
class Solution11 {
    //思路：本题要考虑两个维度：一个是身高，一个是位序，因此与分糖果类似，要分开来考虑
    //首先按身高降序排序（身高相同的话则k小的站前面），因为k表示前面有几个人高，而身高降序就是按照身高来排的，因此根据k的下标直接插入到其对应的位置即可。
    //时间复杂度：O(nlog n + n^2)
    //空间复杂度：O(n)
    public int[][] reconstructQueue(int[][] people) {
        //身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        LinkedList<int[]> queue = new LinkedList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }

        //将链表转为二维数组
        //法一：
        //return queue.toArray(new int[people.length][]);

        //法二：
        //将链表转为二维数组
        int row = queue.size();
        int col = queue.peek().length;
        int[][] result = new int[row][col];
        int i=0;
        for (int j = 0; j < row; j++) {
            result[j] = queue.poll();
        }
        return result;
    }
}
