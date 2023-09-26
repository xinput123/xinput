package com.xinput.leet.linknode;

import com.xinput.model.ListNode;
import com.xinput.util.ListUtils;
import com.xinput.util.NodeUtils;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class Code206 {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.create(ListUtils.newArrayList(1, 2, 3, 4));
        listNode.print();

        reverseList(listNode).print();
    }

    public static ListNode reverseList(ListNode head) {
        // 判空
        if (head == null) {
            return head;
        }

        // 反转链表的特征就是 head 和 tail 的位置 完全反过来了
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next; // 临时指针，用于记录 cur指针的下一个指针
            // cur 的下一个指针已经改变方向了
            cur.next = pre;
            pre = cur;
            // 将临时指针的值再写给cur
            cur = temp;
        }

        return pre;
    }

}
