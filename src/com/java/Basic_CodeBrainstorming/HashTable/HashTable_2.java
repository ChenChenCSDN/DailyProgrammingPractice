package com.java.Basic_CodeBrainstorming.HashTable;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: HashTablt_2
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/10 10:55
 * @Version 1.0
 */
public class HashTable_2 {
    @Test
    public void test() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] result = intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length > nums2.length ? nums1.length : nums2.length];//交集元素数组，最大长度为较长数组的长度
        Map<Integer, Integer> map1 = new HashMap<>();//哈希表，Entry<数组元素，数组元素出现的次数>
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums1) {//遍历数组nums1，将其数组元素及其出现次数存入哈希表
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        for (int i : nums2) {//遍历数组nums2，将其数组元素及其出现次数存入哈希表
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }
        Set<Integer> integers = map1.keySet();//将哈希表map1中的key取出
        int index = 0;//索引下标
        for (Integer integer : integers) {//遍历由map1中的key组成的set
            if (map2.containsKey(integer)) {//map2中也含有该key，说明存在交集
                result[index++] = integer;
            }
        }
        //实际数组大小为0到index，返回截取后新的数组
        return Arrays.copyOfRange(result, 0, index);
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        //升序排序原数组
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();//用于存储相交的元素
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {//遍历数组
            if (nums1[i] > nums2[j]) {//第一个数组元素大，移动第二个数组的指针
                j++;
            } else if (nums1[i] < nums2[j]) {//第二个数组元素大，移动第一个数组的指针
                i++;
            } else {//元素相同，说明相交，插入到集合中
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];//结果数组
        int index = 0;
        for (Integer integer : set) {//集合转为数组
            result[index++] = integer;
        }
        return result;
    }
}
