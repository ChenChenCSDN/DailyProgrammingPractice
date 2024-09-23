package com.java.Basic_CodeBrainstorming.HashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: HashTable_4
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/12 21:55
 * @Version 1.0
 */
public class HashTable_4 {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();//Entry<数组元素，对应下标>
        int temp;
        for (int i = 0; i < nums.length; i++) {
            temp = target - nums[i];//temp为target与当前元素只差
            if (map.containsKey(temp) != false) {//元素之差存在，说明符合两数相加
                result[1] = i;
                result[0] = map.get(temp);
            }
            map.put(nums[i], i);//元素之差不存在，放入到map中
        }
        return result;
    }
}
