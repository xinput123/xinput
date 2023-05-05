package com.xinput.leet.sort;

import com.xinput.model.ListNode;

/**
 * 21: 合并两个有序链表 https://leetcode.cn/problems/merge-two-sorted-lists/description/
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class Code21 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(0);
        listNode1.add(new ListNode(2));
        listNode1.add(new ListNode(4));
        listNode1.add(new ListNode(6));
        listNode1.add(new ListNode(8));
        System.out.print("listNode1 : ");
        listNode1.print();

        ListNode listNode2 = new ListNode(1);
        listNode2.add(new ListNode(3));
        listNode2.add(new ListNode(5));
        System.out.print("listNode2 : ");
        listNode2.print();

//        ListNode listNode = mergeTwoLists(listNode1, listNode2);
//        listNode.print();
        mergeTwoLists2(listNode1,listNode2).print();
    }

    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode preHead = new ListNode(0);
        ListNode pre = preHead;

        while (null != list1 && null != list2){
            if(list1.val <= list2.val){
                pre.next = list1;
                list1 = list1.next;
            }else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }

        pre.next = null == list1 ? list2 : list1;
        return preHead.next;
    }

    /**
     * 思路：使用迭代的方式。
     * 如果 l1 和 l2 有任意一个为空链表时，则不需要判断。
     * 当 l1 和 l2 都不是空链表时，判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，
     * 当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (null != list1 && null != list2) {
            if(list1.val <= list2.val){
                prev.next = list1;
                list1 = list1.next;
            }else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        prev.next = null == list1 ? list2 : list1;
        return prehead.next;
    }
}