package com.java.Basic_algorithms.String;

import org.junit.Test;

/**
 * ClassName: String_2
 * Package: com.java.Basic_algorithms.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/1 12:15
 * @Version 1.0
 */
public class String_2 {
    @Test
    public void test() {
        System.out.println(Solution2.reverse(-2147483648));
    }
}

class Solution2 {
    public static int reverse(int x) {
        long res=0;//用long存储int
        int t=0;//存储尾数
        while (x != 0) {
            t=x%10;
            res = res*10+t;//尾数反转
            x/=10;
        }
        //若（int）re==res，则res在int范围内，否则不一致
       return (int)res == res? (int)res : 0;
    }
}
