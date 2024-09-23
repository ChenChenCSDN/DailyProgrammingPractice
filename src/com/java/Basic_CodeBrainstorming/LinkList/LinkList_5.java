package com.java.Basic_CodeBrainstorming.LinkList;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: LinkList_5
 * Package: com.java.Basic_CodeBrainstorming.LinkList
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/8 11:18
 * @Version 1.0
 */
public class LinkList_5 {
    @Test
    public void test() {

    }

    //法一：分别遍历两个链表，得出其长度差，对长的链表移动长度差的步长，然后同时移动，判断是否有相交结点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;//链表长度
        ListNode curA = headA, curB = headB;//当前操作指针
        while (curA != null) {//遍历链表A，得出长度
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {//遍历链表B，得出长度
            lenB++;
            curB = curB.next;
        }
        //重置操作指针的位置
        curA = headA;
        curB = headB;
        ListNode q = (lenA - lenB) > 0 ? curA : curB;//比较长度，将q赋值给较长的链表
        for (int i = 0; i < Math.abs((lenA - lenB)); i++) {//较长链表移动长度差步长
            q = q.next;
        }
        if ((lenA - lenB) > 0) {//更新操作指针的位置
            curA = q;
        } else {
            curB = q;
        }
        while (curA != null && curB != null) {//同时进行移动，判断是否相交
            if (curA == curB) {
                return curB;
            } else {
                curA = curA.next;
                curB = curB.next;
            }
        }
        //运行至此，说明不存在相交的结点，返回null
        return null;
    }

    //法二：哈希表法（用set实现）：将链表A中的结点存入到哈希表中，再遍历链表B，若B中结点命在哈希表中命中，则存在相交结点
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new LinkedHashSet<>();//哈希表，用于存储链表中的结点
        while (headA != null) {//将链表A中的结点存入到哈希表中
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {//遍历链表B
            if (set.contains(headB) == true) {//B中的结点命中哈希表，说明有相交的结点
                return headB;
            }
            headB = headB.next;

        }
        //执行至此说明哈希表未命中，说明不存在相交的结点
        return null;
    }

    //法二：哈希表（用map实现）：将链表A中的结点存入到哈希表中，再遍历链表B，若B中结点命在哈希表中命中，则存在相交结点
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        Map<ListNode,Integer> map = new HashMap<>();//哈希表，用于存储链表中的结点
        while (headA != null) {//将链表A中的结点存入到哈希表中
            map.put(headA,headA.val);
            headA=headA.next;
        }
        while (headB != null) {//遍历链表B
            if (map.containsKey(headB)==true) {//B中的结点命中哈希表，说明有相交的结点
                return headB;
            }
            headB=headB.next;
        }
        //执行至此说明哈希表未命中，说明不存在相交的结点
        return null;
    }
}
