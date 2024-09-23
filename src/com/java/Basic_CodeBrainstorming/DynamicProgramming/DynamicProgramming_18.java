package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import com.sun.org.apache.bcel.internal.generic.FALOAD;

import java.security.interfaces.ECKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * ClassName: DynamicProgramming_18
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/14 10:00
 * @Version 1.0
 */
public class DynamicProgramming_18 {
    public static void main(String[] args) {
        new Solution18().wordBreak2("aaaaaaa", new ArrayList<String>(Arrays.asList("aaaa", "aaa")));
    }
}

class Solution18 {
    private HashSet<String> set;
    private int[] memory;

    /**
     * 回溯法+记忆化：本题的思路是将字符串依次拆分成字串，然后判断该子串是否在字典中即可，思路同分割回文字串，由于回溯算法的本质是递归，因此会出现判断重复字串的问题，因此可以使用记忆数组，
     * 将不满足条件的字符串记录下来，后续再判断该字符串时直接返回结果。
     * 时间复杂度：O(2^n)
     *
     * @param s        待判断字符串
     * @param wordDict 单词字典
     * @return true-字典单词能组成s false-字典单词不能组成s
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        memory = new int[s.length()];
        return BackTrace(s, 0);
    }


    public boolean BackTrace(String s, int startIndex) {
        if (startIndex == s.length()) {
            return true;
        }
        //从stratIndex为起点的字符串不满足条件，直接返回false
        if (memory[startIndex] == -1)
            return false;
        for (int i = startIndex; i < s.length(); i++) {
            String sub = s.substring(startIndex, i + 1);//拆分字符串中的单词
            //拆分出来的单词无法匹配
            if (!set.contains(sub)) {
                continue;
            }
            boolean flag = BackTrace(s, i + 1);
            if (flag) return true;
        }
        //从startIndex开始的字符串不满足条件，记录到数组中
        memory[startIndex] = -1;
        return false;
    }

    /**
     * 动态规划：此题可以将字典中的单词看成一个一个的物品，字符串看成背包，题意转为能否将物品恰好放入背包中，可任取，故属于完全背包问题。
     * 确定dp数组及其下标含义：dp[i]表示长度为i的字符串，dp[i]为true，表示可以拆分成一个或多个字典里的单词。
     * 确定dp递归公式：思路为从0~s.length遍历字符串，判断截取出来的字符串是否可由字典单词组成，若可以则对应字符串长度下标的dp[clip.length]=true，而当前字符串能否放入取决于放入前
     * 的字符串能否由字典中的单词组成。
     * 确定dp数组初始化：因为后面的dp元素由前面的推导而来，故dp[0]初始化为true
     * 确定dp数组遍历顺序：此题必须先遍历背包，后遍历物品，即属于完全背包中的排列问题，因为： 拿 s = "applepenapple", wordDict = ["apple", "pen"] 举例。
     * <p>
     * "apple", "pen" 是物品，那么我们要求 物品的组合一定是 "apple" + "pen" + "apple" 才能组成 "applepenapple"。
     * <p>
     * "apple" + "apple" + "pen" 或者 "pen" + "apple" + "apple" 是不可以的，那么我们就是强调物品之间顺序。
     * <p>
     * 所以说，本题一定是 先遍历 背包，再遍历物品。
     * 时间复杂度：O(n*m），空间复杂度：O(n)
     *
     * @param s        待判断字符串
     * @param wordDict 单词字典
     * @return true-字典单词能组成s false-字典单词不能组成s
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        //初始化dp数组
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        //先遍历背包（字符串）
        for (int i = 1; i <= s.length(); i++) {
            //后便利物品（字典中的单词）
            for (String word : wordDict) {
                int len = word.length();
                //dp[i - len]是判断放入当前子字符串前，之前的子字符串是否为true
                //word.equals(s.substring(i - len, i))是用于判断当前截取的子字符串是否在单词字典中
                if (i >= len && dp[i - len] && word.equals(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        //dp[s.length]表明长度为s.length的字符串能否由一个或多个字典中的单词组成
        return dp[s.length()];
    }
}
