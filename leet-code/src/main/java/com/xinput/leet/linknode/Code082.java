package com.xinput.leet.linknode;

import com.xinput.model.ListNode;
import com.xinput.util.ListUtils;
import com.xinput.util.NodeUtils;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/
 * 82. 删除链表重复元素
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */
public class Code082 {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.create(ListUtils.newArrayList(1, 1, 2, 2, 3, 3, 4, 4, 5));
        listNode.print();
        deleteDuplicates(listNode).print();

        listNode = NodeUtils.create(ListUtils.newArrayList(1, 2, 2, 3, 3, 4, 4, 5));
        listNode.print();
        deleteDuplicates(listNode).print();

        listNode = NodeUtils.create(ListUtils.newArrayList(1, 2, 3, 3, 4, 4, 5));
        listNode.print();
        deleteDuplicates(listNode).print();

        listNode = NodeUtils.create(ListUtils.newArrayList(1, 1, 1, 2, 3));
        listNode.print();
        deleteDuplicates(listNode).print();
    }

    public static ListNode deleteDuplicates(ListNode head) {
        // 判空
        if (head == null || head.next == null) {
            return head;
        }

        // 因为删除可能设计到头结点，所以定义个虚拟的节点，统一操作
        ListNode dumyHead = new ListNode(-1, head);
        ListNode pre = dumyHead;
        // 我们这里考虑使用双指针的思想
        // 比较两个指针之间的数据是否相同
        ListNode first = pre.next;
        ListNode second = first.next;

        int sameNum = 0;
        while (first != null && second != null) {
            if (first.val != second.val) { // 数值不同
                if (sameNum == 0) {
                    pre.next = first;
                    pre = pre.next;
                } else {
                    pre.next = second;
                }
                first = pre.next;
                second = first.next;
                sameNum = 0;
            } else { // 数值相同
                sameNum++;
                second = second.next;
                if (second == null) {
                    if (sameNum > 0) {
                        pre.next = null;
                        break;
                    }
                }
            }
        }

        return dumyHead.next;
    }

}
