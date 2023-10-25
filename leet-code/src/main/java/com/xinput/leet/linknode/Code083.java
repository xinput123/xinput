package com.xinput.leet.linknode;

import com.xinput.model.ListNode;
import com.xinput.util.ListUtils;
import com.xinput.util.NodeUtils;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/
 * 83. 删除链表重复元素
 * <p>
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * <p>
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */
public class Code083 {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.create(ListUtils.newArrayList(1, 1, 2, 3, 3));
        listNode.print();

        deleteDuplicates(listNode).print();
    }

    public static ListNode deleteDuplicates(ListNode head) {
        // 判空
        if (head == null || head.next == null) {
            return head;
        }

        // 因为删除可能设计到头结点，所以定义个虚拟的节点，统一操作
        ListNode dumyHead = new ListNode(head.val - 1, head);

        ListNode pre = dumyHead;
        ListNode cur = head; // 使用一个节点用来代表当前遍历的节点
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = pre.next;
        }

        return dumyHead.next;
    }

}
