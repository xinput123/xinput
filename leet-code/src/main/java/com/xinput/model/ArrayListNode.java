package com.xinput.model;

/**
 * 单向链表
 */
public class ArrayListNode {

    // 链表数据个数
    private int size;

    // 虚拟头节点
    private ListNode head;

    public ArrayListNode() {
        size = 0;
        head = new ListNode(-1);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        ListNode cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    public boolean addAtHead(int val) {
        return addAtIndex(0, val);
    }

    public boolean addAtTail(int val) {
        return addAtIndex(size, val);
    }

    public boolean addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return false;
        }

        // 找到要插入节点的前序节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        // 创建节点
        ListNode newNode = new ListNode(val);

        // 增加新的节点
        newNode.next = pre.next;
        pre.next = newNode;
        size++;
        System.out.println(String.format("在[%s]位置插入链表[%s]", index, val));
        return true;
    }

    /**
     * 删除节点
     */
    public boolean deleteAtIndex(int index) {
        if (index < 0 || index > size) {
            return false;
        }

        // 找到要删除节点的前置节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = pre.next.next;
        size--;
        return true;
    }

    public void print() {
        ListNode node = head.next;

        boolean isNotNull = node != null;
        if (isNotNull) {
            System.out.print("链表: [");
        }
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.val + " --> ");
            } else {
                System.out.print(node.val);
            }
            node = node.next;
        }
        if (isNotNull) {
            System.out.print("]");
        }
        System.out.println("\n");
    }
}
