package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: BackTrace_14
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/18 10:56
 * @Version 1.0
 */
public class BackTrace_14 {
    @Test
    public void test() {
        new Solution14().solveNQueens(4);
    }
}

class Solution14 {
    List<List<String>> result = new ArrayList<>();// 存放结果的集合

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];// 棋盘
        for (char[] chars : chessboard) {//初始化棋盘，全置为'.'
            Arrays.fill(chars, '.');
        }
        solveNQueensTrace(chessboard, 0, n);
        for (List<String> strings : result) {
            System.out.println(strings);
        }
        return result;
    }

    public void solveNQueensTrace(char[][] c, int row, int n) {
        if (row == n) {// 递归到棋盘最底层（叶子结点），收割结果
            List<String> path = new ArrayList<>();
            for (char[] chars : c) {// 将当前棋盘的结果转为string，存入result结果集中
                path.add(String.valueOf(chars));
            }
            result.add(path);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(c, row, col, n)) {// 验证当前位置是否合法，合法就放
                c[row][col] = 'Q';// 放置皇后
                solveNQueensTrace(c, row + 1, n);
                c[row][col] = '.';// 回溯，撤销皇后
            }
        }
    }

    public boolean isValid(char[][] c, int row, int col, int n) {
        //判断是否同列
        for (int i = 0; i < n; i++) {
            if (c[i][col] == 'Q')
                return false;
        }

        // 检查左上方的对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (c[i][j] == 'Q') {
                return false;
            }
        }

        // 检查右上方的对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (c[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
