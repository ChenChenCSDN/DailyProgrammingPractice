package com.java.Basic_CodeBrainstorming.LinkList;

/**
 * ClassName: LinkList_4
 * Package: com.java.Basic_CodeBrainstorming.LinkList
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/7 14:14
 * @Version 1.0
 */
public class LinkList_4 {
    //法一：先遍历整个链表，确定其长度，然后找到n前面的元素，进行删除n即可
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headNode = new ListNode(-1, head);//头结点指向首元结点
        ListNode cur = headNode;
        int length = 0;//链表长度
        while (cur.next != null) {//遍历链表，得出链表长度
            length++;
            cur = cur.next;
        }
        cur = headNode;//更新指针位置
        int index = 0;
        while (index++ < (length - n)) {//找到n前面的元素
            cur = cur.next;
        }
        //删除n所指向的元素
        cur.next = cur.next.next;
        return headNode.next;
    }

    //法二：双指针，fast和slow指针均先指向头结点，先让fat移动(n+1)步，然后fast和slow一起移动，直至fast为null，，此时slow指向n的前一个结点
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode headNode = new ListNode(-1, head);//头结点指向首元结点
        ListNode fast = headNode, slow = headNode;//fast和slow均指向头结点
        while (n-- >= 0) {//fast移动(n+1)步
            fast = fast.next;
        }
        while (fast != null) {//fast和slow同时移动，直至fast==null
            fast = fast.next;
            slow = slow.next;
        }
        //此时slow指向n的前一个元素，删除n即可
        slow.next = slow.next.next;
        return headNode.next;
    }
}
