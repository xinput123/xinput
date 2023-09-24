package com.xinput.leet.linknode;

/**
 * https://leetcode.cn/problems/design-linked-list/
 * 707. 设计链表
 */
public class Code707 {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.print();

        list.addAtTail(3);
        list.print();

//        list.addAtIndex(1, 2);
//        System.out.println(list.get(1));
//        list.deleteAtIndex(1);
//        System.out.println(list.get(1));
    }


}

class MyLinkedList {
    // 存储链表的元素个数
    int size;
    // 虚拟头结点
    ListNode head;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index > size) {
            return -1;
        }

        // 找到该节点
        ListNode pre = head;
        for (int i = 0; i <= index; i++) {
            pre = pre.next;
        }

        return pre.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }

        // 找到要插入节点的前驱节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        size++;
        ListNode newNode = new ListNode(val);
        newNode.next = pre.next;
        pre.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        // 找到要删除节点的前驱节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = pre.next.next;
    }

    public void print() {
        ListNode node = head.next;
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.val + " --> ");
            } else {
                System.out.print(node.val);
            }
            node = node.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
