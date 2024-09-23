package com.java.Basic_CodeBrainstorming.DoublePoint;

import org.junit.Test;

/**
 * ClassName: Double_6
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/20 17:35
 * @Version 1.0
 */
public class DoublePoint_6 {
    @Test
    public void test(){
        ListNode headNode = new ListNode(-1, null);
        for (int i = 4; i > 0; i--) {
            ListNode p = new ListNode(i, null);
            p.next = headNode.next;
            headNode.next = p;
        }
        ListNode listNode = removeNthFromEnd2(headNode.next,2);
    }

    //双指针的经典应用，如果要删除倒数第n个节点，让fast移动n步，然后让fast和slow同时移动，直到fast指向链表末尾。删掉slow所指向的节点就可以了。
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headNode = new ListNode(-1, head);
        ListNode slow = headNode, fast = headNode;
        while (n-- > 0)
            fast = fast.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next=slow.next.next;
        return headNode.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n){
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        // 只要快慢指针相差 n 个结点即可
        for (int i = 0; i <= n  ; i++){
            fastIndex = fastIndex.next;
        }

        while (fastIndex != null){
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        //此时 slowIndex 的位置就是待删除元素的前一个位置。
        //具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next;
    }
}
