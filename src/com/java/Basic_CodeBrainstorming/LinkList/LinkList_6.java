package com.java.Basic_CodeBrainstorming.LinkList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: LinkList_6
 * Package: com.java.Basic_CodeBrainstorming.LinkList
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/8 14:45
 * @Version 1.0
 */
public class LinkList_6 {
    //法一：哈希表法，遍历整个链表，将链表中的结点及出现次数加入到哈希表中，当次数为2时，说明该结点为环形链表的入口结点
    public ListNode detectCycle(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();//哈希表，存储<结点，结点出现次数>
        while (head != null) {//遍历链表
            map.put(head, map.getOrDefault(head, 0) + 1);//存储<结点，结点出现次数>
            if (map.get(head) == 2) {//结点出现次数为2.说明为环形结点入口
                return head;
            }
        }
        //执行至此，说明不存在环形链表
        return null;
    }

    //法二：哈希表法，遍历整个链表，将链表存储在set中，若contains(head)==true，说明已存入当前结点，即为环形结点入口
    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> set = new LinkedHashSet<>();//哈希表，存储链表中的结点
        while (head != null) {//遍历链表
            if (set.contains(head)) {//哈希表中已存在该结点，说明该结点为环形结点入口
                return head;
            } else {//哈希表中不存在该结点，将其放入到哈希表中
                set.add(head);
            }
            head = head.next;
        }
        //执行至此，说明不存在环形链表
        return null;
    }
}
