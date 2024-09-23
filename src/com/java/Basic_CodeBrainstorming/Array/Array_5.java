package com.java.Basic_CodeBrainstorming.Array;

import org.junit.Test;

import javax.tools.ForwardingFileObject;
import java.util.Arrays;

/**
 * ClassName: Array_5
 * Package: com.java.Basic_CodeBrainstorming.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/4 14:57
 * @Version 1.0
 */
public class Array_5 {
    @Test
    public void test() {
        int[][] revArray = generateMatrix(2);
        for (int[] ints : revArray) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] revArray = new int[n][n];//用于存储顺时针排序后的数组
        int loop = 0;//循环次数
        int start = 0;//每次循环的起始下标
        int count = 1;//填充数字，自递增
        int i, j;
        while (loop++ < n / 2) {
            //上层从左到右复制
            for (i = start; i < n - loop; i++) {
                revArray[start][i] = count++;
            }
            //右层从上到下赋值
            for (j = start; j < n - loop; j++) {
                revArray[j][i] = count++;
            }
            //下层从右到左赋值
            for (; j >= loop; j--) {
                revArray[i][j] = count++;
            }
            //左层从下到上赋值
            for (; i >= loop; i--) {
                revArray[i][j] = count++;
            }
            start++;
        }
        if (n % 2 != 0) {
            revArray[n / 2][n / 2] = n * n;//非偶数数组，中间元素为n的平方
        }
        return revArray;

    }
}
