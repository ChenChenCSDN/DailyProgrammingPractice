package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: GreedyAlgorithm_14
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * <p>
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * <p>
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 *
 * @Author chen_sir
 * @Create 2024/5/16 10:07
 * @Version 1.0
 */
public class GreedyAlgorithm_14 {
    @Test
    public void test() {
        String s = "caedbdedda";
        new Solution14().partitionLabels(s);
    }
}

class Solution14 {
    /*
    思路：因为字符串片段要满足同一字母最多出现在一个片段中，因此需要判断当前字母是否在后面出现，容易联想到使用哈希表，来记录字母的使用情况，又因为每次判断的时候还需要考虑
         当前字符片段之前出现过那些字母，因此需要一个StringBuilder的temp变量来暂存整个片段，从头遍历来获取每个字母，若当前片段的所有字母在整个字符串中出现的次数为0，
         说明当前片段满足划分条件；注意要判断的是子片段，因此每次判断成功后要将temp清空，来进行下次判断
    贪心思路：局部最优：划分的片段尽可能的长
            全局最优：整个字符串划分的片段最多
    时间复杂度：O(n)
    空间复杂度：O(1)，因为哈希表大小是固定的（最多26个字母）
    */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();//存放每个字符串长度的结果集
        Map<Character, Integer> map = new HashMap<>();//哈希表，存放字符串中每个字符出现的次数
        for (int i = 0; i < s.length(); i++) {//统计s中每个字符出现的次数
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        StringBuilder temp = new StringBuilder();//字符串片段
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) != 0) {//当前字符在后面仍会出现，拼接到temp后，出现次数减1
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                temp.append(s.charAt(i));
            }
            int j = 0;
            while (j < temp.length()) {//遍历当前字符串片段中每个字符出现的次数，若均为0，则为划分后的片段
                if (map.get(temp.charAt(j)) != 0)
                    break;
                j++;
            }
            if (j == temp.length()) {//当前字符串片段中的字符均未在后面出现
                System.out.println(temp);
                result.add(j);//将字符串长度存入结果集中
                temp.delete(0, j);//清空片段，开始下次判断
            }

        }
        System.out.println(result);
        return result;
    }

    //思路：在遍历的过程中相当于是要找每一个字母的边界，如果找到之前遍历的所有字母的最远边界，说明这个边界就是分割点了
    /*
     * 步骤：
     *   1、统计每一个字符最后出现的位置
     *   2、从头遍历字符，并更新字符的最远出现下标，如果找到字符最远位置下标和当前下标相等了，则找到了分割点
     * */
    //时间复杂度：O(n)
    //空间复杂度：O(1)，使用的hash数组是固定大小
    public List<Integer> partitionLabels2(String s) {
        List<Integer> result = new ArrayList<>();//存放每个字符串长度的结果集
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {//统计每一个字符最后出现的位置
            hash[s.charAt(i) - 'a'] = i;
        }
        int end = Integer.MIN_VALUE;
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(hash[s.charAt(i) - 'a'], end);//找到字符出现的最远边界
            if (i == end) {//到达字符出现的最远边界，记录字符片段个数
                result.add(i - start);
                start = i;//记录字符片段的起点
            }
        }
        System.out.println(result);
        return result;
    }
}

