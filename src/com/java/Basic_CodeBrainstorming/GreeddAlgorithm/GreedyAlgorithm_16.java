package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: GreedyAlgorithm_16
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
 * <p>
 * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 10
 * 输出: 9
 * 示例 2:
 * <p>
 * 输入: n = 1234
 * 输出: 1234
 *
 * @Author chen_sir
 * @Create 2024/5/18 10:34
 * @Version 1.0
 */
public class GreedyAlgorithm_16 {
    @Test
    public void test() {
        int n = 100;
        System.out.println(new Solution16().monotoneIncreasingDigits2(n));
    }
}

class Solution16 {
    //法一（超出时间限制）：暴力法求解，直接判断n的每一位是否递增
    //时间复杂度：O(n × m) m为n的数字长度
    //空间复杂度：O(1)
    public int monotoneIncreasingDigits(int n) {
        for (int i = n; i >= 9; i--) {//暴力遍历n的所有数
            if (isDescndNumber(i) == true)//判断是否递增
                return i;
        }
        return 0;
    }

    public boolean isDescndNumber(int n) {
        int max = 10;
        while (n != 0) {//计算n的每一位，放入list中
            int t = n % 10;
            if (max >= t) max = t;
            else return false;
            n /= 10;
        }
        return true;
    }

    /*
        法二（贪心法）：例如：98，一旦出现strNum[i - 1] > strNum[i]的情况（非单调递增），首先想让strNum[i - 1]--
                     然后strNum[i]给为9，这样这个整数就是89，即小于98的最大的单调递增整数。需要从后往前遍历，比如332，若从i前往后遍历，
                     结果就是329，此时2又小于3了。而从后往前遍历332，得到的结果时332->329->299。
        思路：一旦出现strNum[i - 1] > strNum[i]的情况，就讲strNum[i - 1]--，然后记录strNum[i]的下标为start，然后将最后一个满足这种情况的下标更新为start，
             最后将start-length的所有元素置为9即可。
    */
    //时间复杂度：O(n)，n 为数字长度
    //空间复杂度：O(n)，需要一个字符串，转化为字符串操作更方便
    public int monotoneIncreasingDigits2(int n) {
        //将n转换为数组，便于操作
        String s = String.valueOf(n);
        char[] c = s.toCharArray();
        int start = c.length;//用于记录更新9的开始位置，初始为最后(即c.length）
        for (int i = c.length - 2; i >= 0; i--) {//判断是否有strNum[i - 1] > strNum[i]的情况
            if (c[i] > c[i + 1]) {//满足strNum[i - 1] > strNum[i]
                c[i]--;
                start = i + 1;//更新记录位置
            }
        }
        for (int i = start; i < c.length; i++) {//将start-length的所有元素置为9
            c[i] = '9';
        }
        return Integer.parseInt(String.valueOf(c));
    }
}