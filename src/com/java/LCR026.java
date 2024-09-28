package com.java;

import java.time.temporal.ValueRange;
import java.util.List;

/**
 * ClassName: LCR026
 * Package: com.java
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/9/27 10:24
 * @Version 1.0
 */

class ListNode {
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class LCR026 {
    public static void main(String[] args) {
        ListNode node4 = new ListNode(5, null);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode head = new ListNode(1, node1);
        reorderList(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        while (temp != null) {
            ListNode pnext = temp.next;
            temp.next = slow.next;
            slow.next = temp;
            temp = pnext;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        while (head != null && head2 != null) {
            ListNode headTemp = head.next;
            ListNode head2Temp = head2.next;
            head.next = head2;
            head = headTemp;
            head2.next = headTemp;
            head2 = head2Temp;
        }
    }
}
