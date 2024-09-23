package com.java.Basic_CodeBrainstorming.LinkList;

import org.junit.Test;

/**
 * ClassName: LinkList_1
 * Package: com.java.Basic_CodeBrainstorming.LinkList
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/5 14:21
 * @Version 1.0
 */
public class LinkList_1 {
    @Test
    public void test() {

    }

    public class ListNode {
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

    /**
     * @param head
     * @param val
     * @description:不添加虚拟结点方式1
     * @return: com.java.Basic_CodeBrainstorming.LinkList.LinkList_1.ListNode
     */
    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {//头指针不为空且值等于val，删除
            head = head.next;
        }
        if (head == null) {//头指针为空或整个链表全由val组成，则head=null
            return null;
        }
        //此时head一定不为null且val的值一定不等于val
        ListNode pre = head;//头结点
        ListNode pnext = null;//临时指针，用于删除元素
        while (pre.next != null) {
            pnext = pre.next;
            if (pnext.val == val) {
                pre.next = pnext.next;
            } else {//头结点移动
                pre = pre.next;
            }
        }
        return head;
    }

    /**
     * @param head
     * @param val
     * @description:不添加虚拟结点方式2
     * @return: com.java.Basic_CodeBrainstorming.LinkList.LinkList_1.ListNode
     */
    public static ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val) {//头指针不为空且值等于val，删除
            head = head.next;
        }
        //执行到此处，head要么为null或者head.val不为val
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == val) {//删除cur的下一个元素值为val的结点，直至不等于
                cur.next = cur.next.next;
            }
            //指针移动
            cur = cur.next;
        }
        return head;//返回头指针
    }

    /**
     * @param head
     * @param val
     * @description:添加虚拟结点方式
     * @return: com.java.Basic_CodeBrainstorming.LinkList.LinkList_1.ListNode
     */
    public ListNode removeElements3(ListNode head, int val) {
        //创建一个虚拟结点指向head的前面
        //用于统一处理第一个结点和其他结点的操作，这样就无需特殊处理头结点
        ListNode headpre = new ListNode(-1, head);
        ListNode temp = headpre;
        while (temp.next!= null) {
            if (temp.next.val == val) {
                temp.next=temp.next.next;
            }else{
                temp=temp.next;
            }
        }
        return headpre.next;//返回头指针
    }

}
