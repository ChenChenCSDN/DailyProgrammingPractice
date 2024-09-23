package com.java.Basic_CodeBrainstorming.StackAndQueue;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: StackAndQueue_7
 * Package: com.java.Basic_CodeBrainstorming.StackAndQueue
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/26 9:46
 * @Version 1.0
 */
public class StackAndQueue_7 {
    @Test
    public void test() {
        int[] nums = {4,1,-1,2,-1,2,3};
        Solution7.topKFrequent2(nums, 2);
    }
}

class Solution7 {
    //法一：创建map存储元素及其出现的次数，然后将map转为list，list存储单位为Entry，再使用Collections接口中的sort方法来对Entry中的出现次数进行降序排序，遍历前k个排序完后的list即可
    //时间复杂度：O(nlogn)，空间复杂度:O(n)
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();//key为数组元素值，value为对应出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());//将map转为list，list中存储的单元为Entry<Integer,Integer>
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {//根据Entry中的Value进行降序排序
                return o2.getValue() - o1.getValue();
            }
        });
        int[] result = new int[k];//存放结果的数组
        int index = 0;
        while (k-- > 0) {//已排序后的list中将前k个值放入结果数组中
            result[index] = list.get(index).getKey();
            index++;
        }
        return result;
    }

    //法二：创建map存储元素及其出现的次数，然后创建大根堆优先级队列，根据map中的value降序放入队列中，队列存放单位为int[],时间复杂度：O(nlogk)，空间复杂度：O(n)
    public static int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();//key为数组元素值,val为对应出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序是从大到小排,出现次数最多的在队头(相当于大顶堆)
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {//依次从队头弹出k个,就是出现频率前k高的元素
            result[i] = pq.poll().getKey();
        }
        return result;
    }

}
