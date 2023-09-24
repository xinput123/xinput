package com.xinput.model;

/**
 * 可通过下边操作的链表
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

    public boolean addHeadNode(int val) {
        return addNode(0, val);
    }

    public boolean addTailNode(int val) {
        return addNode(size, val);
    }

    public boolean addNode(int index, int val) {
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
        return true;
    }

    /**
     * 删除节点
     */
    public boolean delNode(int index) {
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
}
