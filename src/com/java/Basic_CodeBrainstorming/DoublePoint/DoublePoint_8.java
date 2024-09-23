package com.java.Basic_CodeBrainstorming.DoublePoint;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: DoublePoint_8
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/21 9:42
 * @Version 1.0
 */
public class DoublePoint_8 {
    //思路：根据环形链表一定会有链表重复的思路，使用哈希表存入链表中的结点，若结点重复出现，则说明该链表为环形链表
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {//遍历链表，存入哈希表中
            if (set.contains(head) == true) {//哈希表命中，说明该链表为环形链表
                return head;
            } else {//未命中，将该结点存入到哈希表中
                set.add(head);
                head = head.next;
            }
        }
        //执行至此说明均未命中，该链表不是环形链表
        return null;
    }
}
