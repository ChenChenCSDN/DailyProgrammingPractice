package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.jar.JarEntry;

/**
 * ClassName: Greedy_1
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * 示例 1:
 * <p>
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1
 *
 * @Author chen_sir
 * @Create 2024/4/21 10:23
 * @Version 1.0
 */
public class GreedyAlgorithm_1 {
    @Test
    public void test() {
        int[] g = {1, 2, 3};
        int[] s = {1, 1};
        System.out.println(new Solution1().findContentChildren(g, s));
    }
}

class Solution1 {
    //自己写的解法：双指针法，若饼干大小满足胃口大小，同时右移一位，不满足则右移饼干大小，使饼干变大
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);// 将胃口升序排序
        Arrays.sort(s);// 将饼干大小升序排序
        int count = 0;// 记录可满足胃口的数量
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {// 饼干大小满足胃口，同时右移一位
                count++;
                i++;
                j++;
            } else// 饼干大小不满足胃口，饼干变大，右移一位
                j++;
        }
        return count;
    }

    //标准答案：优先考虑饼干，小饼干先喂饱小胃口
    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);// 将胃口升序排序
        Arrays.sort(s);// 将饼干大小升序排序
        int count = 0;// 记录可满足胃口的数量
        int j = 0;
        for (int i = 0; i < s.length && j < g.length; i++) {// 遍历饼干
            if (s[i] >= g[j]) {// 当前饼干大小满足胃口，胃口右移一位
                j++;
            }
        }
        return j;
    }
}
