package com.xinput.leet.linknode;

import com.xinput.model.ListNode;
import com.xinput.util.ListUtils;
import com.xinput.util.NodeUtils;

/**
 * https://leetcode.cn/problems/remove-linked-list-elements/
 * 213. 移除链表元素
 */
public class Code203 {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.create(ListUtils.newArrayList(1, 2, 3, 4));
        listNode.print();

        removeElements(listNode, 3).print();
    }

    public static ListNode removeElements(ListNode head, int val) {
        // 判空
        if (head == null) {
            return head;
        }

        // 因为删除可能设计到头结点，所以定义个虚拟的节点，统一操作
        ListNode dumyHead = new ListNode(-1, head);
        ListNode pre = dumyHead;
        ListNode cur = head; // 使用一个节点用来代表当前遍历的节点

        while (null != cur) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return dumyHead.next;
    }

}
