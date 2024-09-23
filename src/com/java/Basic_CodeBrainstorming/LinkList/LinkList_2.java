package com.java.Basic_CodeBrainstorming.LinkList;

import org.junit.Test;

import java.awt.*;

/**
 * ClassName: LinkList_2
 * Package: com.java.Basic_CodeBrainstorming.LinkList
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/6 10:06
 * @Version 1.0
 */

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class LinkList_2 {
    @Test
    public void test() {
        ListNode head = new ListNode(-1, null);
        for (int i = 0; i < 4; i++) {
            ListNode p = new ListNode(i + 1);
            p.next = head.next;
            head.next = p;
        }
        ListNode listNode = reverseList3(head.next);
    }

    //法一：虚结点方法，在首元结点前面加一个头指针，便于操作整个链表
    public ListNode reverseList(ListNode head) {
        ListNode pre = new ListNode(-1, head);//头指针指向首元结点之前
        ListNode p = pre.next;//操作指针
        pre.next = null;
        ListNode q = p;//记录当前移动指针的下一个元素，防止断链
        while (p != null) {//遍历链表
            q = p.next;
            //头插法插入元素实现原地逆置
            p.next = pre.next;
            pre.next = p;
            //更新p的位置
            p = q;
        }
        return pre.next;//返回首元结点的位置
    }

    public ListNode reverseList2(ListNode head) {
        if (head != null) {
            reverseList2(head.next);
        }
        return null;//返回首元结点的位置
    }

    //递归方法1：从后往前递归
    public ListNode reverseList3(ListNode head) {
        // 边缘条件判断
        if (head == null) return null;
        if (head.next == null) return head;

        // 递归调用，翻转第二个节点开始往后的链表
        ListNode last = reverseList3(head.next);
        // 翻转头节点与第二个节点的指向
        head.next.next = head;
        // 此时的 head 节点为尾节点，next 需要指向 NULL
        head.next = null;
        return last;
    }

    //递归方法2：从前往后递归
    public ListNode reverseList4(ListNode head) {
        return reverse(null, head);
    }

    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) {//递归到链表尾，退出
            return pre;
        }
        ListNode temp = cur.next;//防止断链
        cur.next = pre;//反转
        return reverse(cur, temp);
    }
}
