package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import javax.tools.ForwardingFileObject;
import java.text.BreakIterator;
import java.util.*;

/**
 * ClassName: BackTrace_15
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * @Author chen_sir
 * @Create 2024/4/19 22:50
 * @Version 1.0
 */
public class BackTrace_15 {
    @Test
    public void test() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        new Solution15().solveSudoku(board);
    }
}

class Solution15 {
    List<char[]> result = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        solveTrace(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public boolean solveTrace(char[][] board) {// 注意点：每次递归都会从左上角的元素开始遍历，优点是逻辑清晰，缺点是有冗余
        for (int i = 0; i < 9; i++) {// 遍历行
            for (int j = 0; j < 9; j++) {// 遍历列
                if (board[i][j] != '.')
                    continue;
                for (char k = '1'; k <= '9'; k++) {// 判断(i, j) 这个位置放k是否合适
                    if (isValid(board, i, j, k)) {
                        board[i][j] = k;// 放置k
                        if (solveTrace(board))// 如果找到合适一组立刻返回
                            return true;
                        board[i][j] = '.';
                    }
                }
                return false; // 当前位置9个数都试完了，都不行，那么就返回false
            }
        }
        return true;// 遍历完没有返回false，说明找到了合适棋盘位置了
    }

    public boolean isValid(char[][] board, int row, int col, char number) {// 判断当前位置元素是否合法（同行同列不重复，且3×3矩阵内不重复）
        for (int i = 0; i < 9; i++) {// 判断同一列是否有重复
            if (board[i][col] == number)
                return false;
        }

        for (int i = 0; i < 9; i++) {// 判断同一列是否有重复
            if (board[row][i] == number)
                return false;
        }

        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
