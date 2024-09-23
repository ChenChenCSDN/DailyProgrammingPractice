package com.java.Basic_algorithms.String;

import org.junit.Test;

import java.nio.charset.spi.CharsetProvider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: String_4
 * Package: com.java.Basic_algorithms.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/2 12:54
 * @Version 1.0
 */
public class String_4 {
    @Test
    public void test() {
        System.out.println(Solution4.isAnagram2("a", "ab"));
    }
}

class Solution4 {
    /**
     * @description: 法一：将字符串中每个字符出现的次数记录到Map中，比较两个Map中每个字符出现的次数是否一样即可
     * @param s 待比较字符串
     * @param t 待比较字符串
     * @return: boolean
     */
    public static boolean isAnagram(String s, String t) {
        char[] arr1 = s.toCharArray();//将字符串s转换成char数组arr1
        char[] arr2 = t.toCharArray();//将字符串t转换成char数组arr2
        Map<Character, Integer> map1 = new HashMap<>(26);//map1用于存储arr1中每个字符及其出现的次数
        Map<Character, Integer> map2 = new HashMap<>(26);//map2用于存储arr2中每个字符及其出现的次数
        for (char c : arr1) {//遍历arr1数组，将其中字符串及出现次数记录到map1中
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : arr2) {//遍历arr2数组，将其中字符串及出现次数记录到map2中
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        return map1.equals(map2);//HashMap中重写了equals方法，若HashMap中的键值对不相同，则返回false；若完全一样，则返回true
    }

    /**
     * @description:法二：先把两个字符串转化为字符数组，然后再对这两个字符数组进行排序，因为相同的字符在排序之后肯定是挨着的，最后再比较这两个排序后的数组的元素是否相同。
     * @param s
     * @param t
     * @return: boolean
     */
    public static boolean isAnagram2(String s, String t) {
        char[] arr1 = s.toCharArray();//将字符串s转换成char数组arr1
        char[] arr2 = t.toCharArray();//将字符串t转换成char数组arr2
        Arrays.sort(arr1);//将数组升序排序
        Arrays.sort(arr2);
        //也可以用Arrays中的APIArrays.equals()方法进行数组的比较
        return Arrays.equals(arr1,arr2);

        //Arrays中的APIArrays.equals()封装了如下代码
//       int length = arr1.length;
//        if (arr2.length != length)
//            return false;
//
//        for (int i=0; i<length; i++)
//            if (arr1[i] != arr2[i])
//                return false;
//
//        return true;
    }
}