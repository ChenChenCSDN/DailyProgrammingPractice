package com.java.Basic_CodeBrainstorming.LinkList;

import org.junit.Test;

/**
 * ClassName: String_2
 * Package: com.java.Basic_CodeBrainstorming.LinkList
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/5 19:56
 * @Version 1.0
 */
class LinkNode {//链表结点
    int val;
    LinkNode next;

    public LinkNode() {
    }

    public LinkNode(int val) {
        this.val = val;
    }

    public LinkNode(int val, LinkNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MyLinkedList {
    static int length;//链表长度
    static LinkNode head = null;//链表头指针
    static LinkNode tail = null;//链表尾指针

    @Test
    public void test() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        SoutList();
        linkedList.addAtTail(3);
        SoutList();
        linkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
        SoutList();
        System.out.println(linkedList.get(1));              // 返回 2
        linkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
        System.out.println(linkedList.get(1));              // 返回 3
    }

    //初始化 MyLinkedList 对象
    public MyLinkedList() {
        //初始化链表，头指针和尾指针都指向null，链表长度为0
        head = new LinkNode(-1, null);
        tail = head;
        length = 0;
    }

    /**
     * @param index
     * @description:获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
     * @return: int 元素下标
     */
    public int get(int index) {//获取下标为index的元素，若下标无效，则返回-1
        if (index >= length) {//下标无效，返回-1
            return -1;
        } else {
            LinkNode p = head;//用于遍历的指针
            while (index-- >= 0) {//找到下标为index的元素位置
                p = p.next;
            }
            return p.val;//返回该元素的值
        }
    }

    //遍历链表
    public void SoutList() {
        for (int i = 0; i < length; i++) {
            System.out.print(get(i));
        }
        System.out.println();
    }

    /**
     * @param val
     * @description: 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
     * @return: void
     */
    public void addAtHead(int val) {
//        LinkNode p = new LinkNode(val);//新的待插入结点
//        if (head.next == null) {//链表为空的情况，插入结点后尾指针指向p
//            tail = p;
//        }
//        //插入结点
//        p.next = head.next;
//        head.next = p;
//        //链表长度加1
//        length++;

        //在链表的最前面插入一个元素，相当于在下标为0的前面插入一个元素
        addAtIndex(0, val);
    }

    /**
     * @param val
     * @description:将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
     * @return: void
     */
    public void addAtTail(int val) {
//        LinkNode p = new LinkNode(val);//新的待插入结点
//        //插入该结点，更新尾指针指向的位置
//        p.next = null;
//        tail.next = p;
//        tail = tail.next;
//        //链表长度加1
//        length++;

        //链表末尾添加元素，相当于在下标为链表长度的位置添加元素
        addAtIndex(length, val);
    }

    /**
     * @param index
     * @param val
     * @description:将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
     * @return: void
     */
    public void addAtIndex(int index, int val) {
        if (index < length) {//index小于链表长度，插入到链表当中
            LinkNode temp = head;//用于遍历的指针
            while (index-- > 0) {//找到索引index的前一个元素位置
                temp = temp.next;
            }
            LinkNode p = new LinkNode(val);//新的待插入结点
            //插入到下标为index的位置上
            p.next = temp.next;
            temp.next = p;
            //链表长度加1
            length++;
        } else if (index == length) {//index等于链表长度，直接插入到链表尾部
            LinkNode p = new LinkNode(val, null);//新的待插入结点
            if (head.next == null) {//链表为空的情况，更新头指针所指向的元素
                head.next = p;
            }
            //插入该结点，更新尾指针指向的位置
            tail.next = p;
            tail = tail.next;
            //链表长度加1
            length++;
        }
    }

    /**
     * @param index
     * @description: 如果下标有效，则删除链表中下标为 index 的节点。
     * @return: void
     */
    public void deleteAtIndex(int index) {
        LinkNode p = head;//用于遍历的指针
        if (index < length) {//下标有效
            while (--index >= 0) {//找到索引index的前一个元素位置
                p = p.next;
            }
            if (p.next.next == null) {//若删除的元素为尾元素，更新尾指针的位置
                tail = p;
            }
            //删除结点
            p.next = p.next.next;
            //链表长度减1
            length--;
        }
    }
}

