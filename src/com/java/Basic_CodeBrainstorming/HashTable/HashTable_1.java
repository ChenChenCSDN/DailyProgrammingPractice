package com.java.Basic_CodeBrainstorming.HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: HashTable_1
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/10 10:20
 * @Version 1.0
 */
public class HashTable_1 {
    //法一：用Map来作为HashTable，Map中存储键值对:Entry<字符，字符出现的次数>
    public boolean isAnagram(String s, String t) {
        char[] array1 = s.toCharArray();//将字符串s转换为char数组
        char[] array2 = t.toCharArray();//将字符串t转换为char数组
        Map<Character, Integer> map1 = new HashMap<>(26);//哈希表，用于存储字符串s中每个字符及其出现的次数
        Map<Character, Integer> map2 = new HashMap<>(26);//哈希表，用于存储字符串t中每个字符及其出现的次数
        for (char c : array1) {//遍历char数组，将其字符及出现次数存入到哈希表map1
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : array2) {//遍历char数组，将其字符及出现次数存入到哈希表map2
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        return map1.equals(map2);//map中的equals方法进行了重写，比较内部元素是否相同
    }

    //法二：用数组来作为HashTable，数组中存储字符及其出现的次数
    public boolean isAnagram2(String s, String t) {
        char[] array1 = s.toCharArray();//将字符串s转换为char数组
        char[] array2 = t.toCharArray();//将字符串t转换为char数组
        int[] record = new int[26];//存储字符及其出现的次数
        for (char c : array1) {//遍历数组array1，对应字符出现次数加1
            record[c - 'a']++;
        }
        for (char c : array2) {//遍历数组array2，对应字符出现次数加减一
            record[c - 'a']--;
        }
        for (int i = 0; i < record.length; i++) {
            if (record[i] != 0) {//数组元素不为0，说明存在不配对的元素
                return false;
            }
        }
        //执行至此说明全部配对
        return true;
    }
}
