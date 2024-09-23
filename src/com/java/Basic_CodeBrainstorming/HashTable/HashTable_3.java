package com.java.Basic_CodeBrainstorming.HashTable;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: HashTable_3
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/12 15:50
 * @Version 1.0
 */
public class HashTable_3 {
    @Test
    public void test() {
        System.out.println(isHappy(19));
    }

    //此方法的的重点在于若该数不是快乐数，会无限循环但始终不变为1，因此若出现重复循环，说明一定不是快乐数
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();//哈希表，用于存储平方和后的数
        int sum;
        while (n != 1 && !set.contains(n)) {//n不为1且n不在set中，说明n未重复循环
            set.add(n);//加入到哈希表中
            sum = 0;
            while (n > 0) {//将n按照每个位置上的数字求平方和
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = sum;//求和后，重置n的大小
        }
        //退出while循环要么是因为n为1，要么是因为平方和出现循环，为1说明是快乐数，出现循环说明不是快乐数
        return n == 1;
    }

}
