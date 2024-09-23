package com.java.Basic_CodeBrainstorming.HashTable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: HashTable_6
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/13 11:07
 * @Version 1.0
 */
public class HashTable_6 {
    @Test
    public void test() {
        System.out.println(canConstruct("aa", "aaab"));
    }

    //法一：使用数组充当哈希表，遍历字符串1来存储字母出现的次数，遍历字符串2来将对应字符出现的次数减1，若最后哈希表中存在大于0的元素，返回false
    public static boolean canConstruct(String ransomNote, String magazine) {
        //将字符串转为char数组
        char[] ransomNoteArray = ransomNote.toCharArray();
        char[] magazineArray = magazine.toCharArray();
        int[] count = new int[26];//存储ransomNote中字符出现的次数
        for (char c : ransomNoteArray) {//遍历源数组，对应字符次数加一
            count[c - 'a']++;
        }
        for (char c : magazineArray) {//遍历目标数组，对应字符次数减一
            count[c - 'a']--;
        }
        //若count数组中有大于0的元素，说明源数组不能由目标数组构成
        for (int i : count) {
            if (i > 0) {
                return false;
            }
        }
        //执行至此说明源数组可由目标数组构成
        return true;
    }

    //法二：使用Map充当哈希表，遍历字符串1来存储字母及其出现的次数，遍历字符串2来将对应字符出现的次数减1，若最后哈希表中存在大于0的元素，返回false
    public static boolean canConstruct2(String ransomNote, String magazine) {
        //将字符串转为char数组
        char[] ransomNoteArray = ransomNote.toCharArray();
        char[] magazineArray = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : ransomNoteArray) {//遍历字符串1，存入到Map<字符，字符出现的次数>中
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : magazineArray) {//遍历字符串2，将对应出现的字符次数减一
            if (map.containsKey(c) == true) {
                map.put(c, map.getOrDefault(c, 0) - 1);
            }
        }
        Set<Character> characters = map.keySet();
        for (Character character : characters) {
            if (map.get(character) > 0) {//最后哈希表中存在大于0的元素，返回false
                return false;
            }
        }
        //执行至此说明源数组可由目标数组构成
        return true;
    }
}
