package com.java.Basic_CodeBrainstorming.LinkList;

import org.junit.Test;

/**
 * ClassName: LinkList_3
 * Package: com.java.Basic_CodeBrainstorming.LinkList
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/7 10:37
 * @Version 1.0
 */

public class LinkList_3 {
    @Test
    public void test() {

    }

    //法一：遍历链表，变动指针位置进行转换
    public ListNode swapPairs(ListNode head) {
        ListNode headNode = new ListNode(-1, head);//创建头结点，指向首元结点
        ListNode cur = headNode;
        ListNode temp;
        ListNode firstNode;
        ListNode lastNode;
        while (cur.next != null && cur.next.next != null) {
            temp = cur.next.next.next;//防止断链
            firstNode = cur.next;
            lastNode = cur.next.next;
            //转换元素
            cur.next = lastNode;
            lastNode.next = firstNode;
            firstNode.next = temp;
            //更新结点位置
            cur = firstNode;
        }
        return headNode.next;//首元结点
    }

    //法二：递归实现（后续递归：从后往前）
    //递归的重点：
        //1.返回值是什么？：交换完成的子链表
        //2.每次递归的小模块要完成什么？：两个结点交换
        //3.终止条件：head为空或者head.next==null
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //获取当前结点的下一个结点
        ListNode next = head.next;
        //进行递归
        head.next=swapPairs2(next.next);
        //连接子链表
        next.next=head;
        return next;
    }


    //法二：递归实现（前序递归：从前往后）
    //递归的重点：
    //1.返回值是什么？：交换完成的子链表
    //2.每次递归的小模块要完成什么？：两个结点交换
    //3.终止条件：head为空或者head.next==null
    public ListNode swapPairs3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head.next.next;
        ListNode next = head.next;
        next.next=head;
        head.next=swapPairs3(p);
        return next;
    }


}

