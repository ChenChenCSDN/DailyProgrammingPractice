package com.java.Basic_CodeBrainstorming.DoublePoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: DoublePoint_7
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/20 19:41
 * @Version 1.0
 */
public class DoublePoint_7 {
    //法一：根据两个链表的长度差，移动较长的链表使其指向的起始位置下标一致，然后同时移动，若遍历到相同的结点，则说明相交，否则说明不相交
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode headNodeA = new ListNode(-1, headA);//链表A的头结点
        ListNode headNodeB = new ListNode(-1, headB);//链表B的头结点
        int lenA = 0, lenB = 0;
        while (headNodeA.next != null) {//遍历链表A，得出其长度
            headNodeA = headNodeA.next;
            lenA++;
        }
        while (headNodeB.next != null) {//遍历链表B，得出其长度
            headNodeB = headNodeB.next;
            lenB++;
        }
        ListNode pa = headA, pb = headB;
        int lengthDist = Math.abs(lenA - lenB);
        //判断哪个链表较长，移动较长的链表
        if (lenA > lenB) {
            while (lengthDist-- > 0) {
                pa = pa.next;
            }
        } else {
            while (lengthDist-- > 0) {
                pb = pb.next;
            }
        }
        //此时链表的起始位置相同，同时移动，判断是否有相交的结点
        while (pa != pb && pa != null) {
            pa = pa.next;
            pb = pb.next;
        }
        //运行至此要么有相同的结点，要么遍历到链表的末尾，为null
        return pa;
    }

    //法二：使用哈希表存储链表A的每一个结点，再遍历链表B中的结点，判断是否命中哈希表，若命中则表示有相交的结点，没命中则表示无相交的结点，时间复杂度为O(n)
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Map<ListNode, Integer> map = new HashMap<>();
        while (headA != null) {//遍历链表A中的每个结点，将其放入到哈希表种
            map.put(headA, headA.val);
            headA = headA.next;
        }
        while (headB != null) {//遍历链表B中的每个结点，判断其是否命中哈希表
            if (map.containsKey(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }
}
