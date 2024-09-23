package com.java.Basic_CodeBrainstorming.HashTable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: HashTable_5
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 * 一采用分为两组，HashMap 存一组，另一组和 HashMap 进行比对。
 * 这样的话情况就可以分为三种：
 * HashMap 存一个数组，如 A。然后计算三个数组之和，如 BCD。时间复杂度为：O(n)+O(n^3)，得到 O(n^3).
 * HashMap 存三个数组之和，如 ABC。然后计算一个数组，如 D。时间复杂度为：O(n^3)+O(n)，得到 O(n^3).
 * HashMap存两个数组之和，如AB。然后计算两个数组之和，如 CD。时间复杂度为：O(n^2)+O(n^2)，得到 O(n^2).
 * 根据第二点我们可以得出要存两个数组算两个数组。
 * 我们以存 AB 两数组之和为例。首先求出 A 和 B 任意两数之和 sumAB，以 sumAB 为 key，sumAB 出现的次数为 value，存入 hashmap 中。
 * 然后计算 C 和 D 中任意两数之和的相反数 sumCD，在 hashmap 中查找是否存在 key 为 sumCD。
 * 算法时间复杂度为 O(n2)。
 *
 * @Author chen_sir
 * @Create 2024/3/13 9:33
 * @Version 1.0
 */
public class HashTable_5 {
    @Test
    public void test() {
        int[] nums1 = {0, 0};
        int[] nums2 = {0, 0};
        int[] nums3 = {0, 0};
        int[] nums4 = {0, 0};

        System.out.println(fourSumCount3(nums1, nums2, nums3, nums4));
    }

    //法一：暴力求解法，四层循环依次遍历数组，找到满足和为0的四个数，但时间复杂度高，为O(n^4)
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int i1 = 0; i1 < nums2.length; i1++) {
                for (int i2 = 0; i2 < nums3.length; i2++) {
                    for (int i3 = 0; i3 < nums4.length; i3++) {
                        if (nums1[i] + nums2[i1] + nums3[i2] + nums4[i3] == 0) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }

    //法二：哈希表法，Entry<两数之和，出现的次数>，创建两个哈希表存放两两数组之和，若两个哈希表之和为0，则符合条件的个数为两数之和出现的次数的乘积，时间复杂度：O(n^2)
    public static int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        //Entry<两数之和，出现的次数>
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums1) {//遍历前两个数组，得出组合相加后的两数之和及其出现的次数
            for (int i1 : nums2) {
                map1.put(i + i1, map1.getOrDefault(i + i1, 0) + 1);
            }
        }

        for (int i : nums3) {//遍历后两个数组，得出组合相加后的两数之和及其出现的次数
            for (int i1 : nums4) {
                map2.put(i + i1, map2.getOrDefault(i + i1, 0) + 1);
            }
        }
        //将两数之和的所有可能取出
        Set<Integer> map1_key = map1.keySet();
        Set<Integer> map2_key = map2.keySet();
        for (Integer i : map1_key) {
            for (Integer j : map2_key) {
                if (i + j == 0) {//若前两个数组的两数之和和后两个数组的两数之和为0，满足
                    result += map1.get(i) * map2.get(j);//由独立事件可知随机组合出现的次数为拆分的事件次数乘积
                }
            }
        }
        return result;
    }

    //法二优化：哈希表法，Entry<两数之和，出现的次数>，创建两个哈希表存放两两数组之和，若两个哈希表之和为0，则符合条件的个数为两数之和出现的次数的乘积，时间复杂度：O(n^2)
    public static int fourSumCount2Last(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        //Entry<两数之和，出现的次数>
        Map<Integer, Integer> countAB = new HashMap<>();
        for (int i : nums1) {//遍历前两个数组，得出组合相加后的两数之和及其出现的次数
            for (int i1 : nums2) {
                countAB.put(i + i1, countAB.getOrDefault(i + i1, 0) + 1);
            }
        }

        for (int i : nums3) {//遍历后两个数组，得出组合相加后的两数之和及其出现的次数
            for (int i1 : nums4) {
                if (countAB.containsKey(-(i + i1)) == true) {
                    result+=countAB.get(-(i+i1));
                }
            }
        }
        return result;
    }

    //前三个数组组合排序组成哈希表，与最后一个数组进行比对，时间复杂度：O(n^3)
    public static int fourSumCount3(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        //Entry<两数之和，出现的次数>
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {//遍历前两个数组，得出组合相加后的两数之和及其出现的次数
            for (int i1 : nums2) {
                for (int i2 : nums3) {
                    map1.put(i + i1 + i2, map1.getOrDefault(i + i1 + i2, 0) + 1);
                }
            }
        }
        //将两数之和的所有可能取出
        for (int i : nums4) {
            if (map1.containsKey(-i) == true) {
                result += map1.get(-i);
            }
        }
        return result;
    }
}
