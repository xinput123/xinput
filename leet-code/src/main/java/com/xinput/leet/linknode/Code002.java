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
public class Code002 {

    public static void main(String[] args) {
        ListNode listNodeA = NodeUtils.create(ListUtils.newArrayList(1, 1, 2, 2, 3, 3, 4, 4, 5));
        ListNode listNodeB = NodeUtils.create(ListUtils.newArrayList(1, 1, 2, 2));
        listNodeA.print();
        getIntersectionNode(listNodeA, listNodeB);
    }

    /**
     * 判断链表是否有相交点，即判断两个链表地址是否相等
     * 我们可以先分析一下，如果两个链表有相交点，那么从相交点之后，所有的元素必然都相同，且长度也相等。
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 先校验是否有空的链表
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;

        while (curA != null) {
            lenA++;
            curA = curA.next;
        }

        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;
        // 为了方便，我们让 headA 作为长的链表
        if (lenA < lenB) {
            int tempLen = lenA;
            lenA = lenB;
            lenB = tempLen;

            ListNode tempNode = curA;
            curA = curB;
            curB = tempNode;
        }

        // 让 curA 和 curB 保持同样的长度
        for (int i = 0; i < lenA - lenB; i++) {
            curA = curA.next;
        }

        // curA 和 curB 的长度已经一致，我们只需判断节点是否相同即可
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }

            curA = curA.next;
            curB = curB.next;
        }

        return null;
    }
}
