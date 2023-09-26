package com.xinput.leet.linknode;

import com.xinput.model.ListNode;
import com.xinput.util.ListUtils;
import com.xinput.util.NodeUtils;

/**
 * https://leetcode.cn/problems/reverse-linked-list-ii/description/
 * 92: 反转链表2
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * @see Code206
 */
public class Code092 {

    public static void main(String[] args) {
        ListNode listNode = NodeUtils.create(ListUtils.newArrayList(1, 2, 3, 4, 5));
        listNode.print();

        reverseBetween(listNode, 2, 4).print();
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 判空
        if (head == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head; // 其实位置
        // 因为我们要反转的链表的起始位置在 left, 所以我们要找到起始位置
        for (int i = 0; i < left - 1; i++) {
            pre = cur;
            cur = cur.next;
        }

        // 从这个位置开始要反转链表了
        for (int i = 0; i < right - left; i++) {
            ListNode temp = cur.next; // 临时指针，用于记录当前指针的下一个指针
            // 让 temp 和 cur 交换位置
            cur.next = cur.next.next;
            temp.next = pre.next;
            pre.next = temp;
        }

        return pre;
    }

}
