package com.java.Basic_algorithms.Array;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: Array_5
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2023/11/15 21:34
 * @Version 1.0
 */
public class Array_5 {
    @Test
    public void test(){
        int[]nums = new int[]{4,1,2,1,2};
        System.out.println(findNumberOnlyonce.singleNumber(nums));
    }
}

class findNumberOnlyonce{
    //方法1：暴力枚举法，选中一个元素，依次遍历数组中的所有元素，用count记录该元素个数，若一轮遍历后count为2，则该元素有重复，若为1，则元素唯一没有重复。
    public static int singleNumber(int[] nums) {
        int count=0;//用于记录数组中重复元素的数目
        for (int i = 0; i < nums.length; i++) { //外层循环，控制所要比较的元素值
            count=0;
            for (int j = 0; j < nums.length; j++) { //内层循环，遍历整个数组进行比较
                if(nums[i] ==nums[j]){  //数组中含相同元素，count++
                    count++;
                }
            }
            if (count == 1) {   //选中元素遍历完一轮后，不存在重复的
                return nums[i];
            }
        }
        return 0;
    }


    /**方法2：根据异或的运算法则对整个数组进行异或即可，剩下的元素就是唯一的元素： 注：异或是对二进制进行异或运算！
            使用异或运算，将所有值进行异或
            异或运算，相异为真，相同为假，所以 a^a = 0 ;0^a = a
            因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
        例：4^1 = 5
           4^1^4 = 1
     */
    public static int singleNumber2(int[] nums) {
        int result=0;   //存储结果
        for(int i:nums){//遍历数组，进行异或
            result ^= i;
        }
        return result;
    }

    //方法3：根据set集合只能存放不重复元素的特性，遍历数组中的元素，然后再一个个添加到集合Set中，如果添加失败，说明以前添加过，就把他给移除掉。当我们把数组中的所有元素都遍历完的时候，集合Set中只会有一个元素，这个就是我们要求的值。
    public static int singleNumber3(int[] nums) {
        Set<Integer>set = new HashSet<>();//集合set用于存放数组中的元素
        for(int i:nums){    //遍历数组，将其插入到set集合中
            if(!set.add(i)){    //set.add()返回false，即集合中存在重复元素，删除重复元素
                set.remove(i);
            }
        }
        //将所有重复的元素删除后，剩下的元素即为唯一不重复的元素
        return (int) set.toArray()[0];
    }

}
