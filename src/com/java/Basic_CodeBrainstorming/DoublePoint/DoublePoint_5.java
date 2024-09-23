package com.java.Basic_CodeBrainstorming.DoublePoint;

import org.junit.Test;

import java.util.List;

/**
 * ClassName: DoublePoint_5
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/20 11:34
 * @Version 1.0
 */
public class DoublePoint_5 {

    //法一：从前往后依次逆置反转
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    @Test
    public void test() {
        ListNode headNode = new ListNode(-1, null);
        for (int i = 0; i < 4; i++) {
            ListNode p = new ListNode(i, null);
            p.next = headNode.next;
            headNode.next = p;
        }
        ListNode listNode = reverseList2(headNode.next);

    }

    //法二：思路同法一，但是使用递归实现
    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null)
            return pre;
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }
}
