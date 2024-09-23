package com.java.Basic_algorithms.String;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: String_3
 * Package: com.java.Basic_algorithms.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/2 11:28
 * @Version 1.0
 */
public class String_3 {
    @Test
    public void test() {
        System.out.println(Solution3.firstUniqChar3("aabb"));
    }
}

class Solution3 {
    /**
     * @param s 待查找的字符串
     * @description:法一：将字符串s转换成char数组，int数组为对应字符的符号位，0表示不重复，1表示重复； 双指针遍历char数组，若存在相同字符，则将对应int数组中的下标元素置为1，遍历int数组，第一个0元素索引即为第一个不重复字符的索引
     * @return: int 第一个不重复字符的索引
     */
    public static int firstUniqChar(String s) {
        int[] array = new int[s.length()];//符号位数组
        char[] temp = s.toCharArray();//将字符串s转换成char数组
        for (int i = 0; i < s.length() - 1; i++) {//双指针遍历数组，看字符是否重复
            for (int j = s.length() - 1; j > i; j--) {
                if (temp[i] == temp[j]) {//字符存在重复，则将对应符号位数组元素置为1
                    array[i] = 1;
                    array[j] = 1;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {//遍历符号位数组，输出第一个为0的元素下标
            if (array[i] == 0) {
                return i;
            }
        }
        //执行到此处说明不存在不重复的字符索引
        return -1;
    }

    public static int firstUniqChar2(String s) {
        Map<Character, Integer> map = new HashMap<>(26);//map存储对应字符及其出现次数
        char[] arr = s.toCharArray();//将字符串s转换成char数组arr
        for (char c : arr) {//遍历arr数组，将字符串及其对应出现次数存入map中
            map.put(c, map.getOrDefault(c, 0) + 1);//map.getOrDefault(c, 0)：若c存在于mao中，则返回其关联的值；否则返回默认值0
        }
        for (int i = 0; i < arr.length; i++) {//遍历每一个字符，并查询其在map中存储的出现次数
            if (map.get(arr[i]) == 1) {//只出现一次
                return i;
            }
        }
        //执行到此处说明不存在只出现一次的字符
        return -1;
    }

    /**
     * @description:使用String的API：indexof和lastindexof，若返回一样的下标，则说明该元素唯一存在
     * @param s
     * @return: int
     */
    public static int firstUniqChar3(String s) {
        for (int i = 0; i < s.length(); i++) {
            if(s.indexOf(s.charAt(i))==s.lastIndexOf(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }
}