package com.xinput.model;

/**
 * 单向链表
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void add(ListNode newNode) {
        // 添加新节点
        if (this.next == null) {
            // 如果下一个节点为空，即可直接添加
            this.next = newNode;
        } else {
            // 否则再次调用Node方法，查找下一个可用的空间
            this.next.add(newNode);
        }
    }


    public void print() {
        // 打印方法，直接打印data数据，并判断是否有下个节点，有的话就继续打印
        if (this.next == null) {
            System.out.println(this.val);
        } else {
            System.out.print(this.val + " --> ");
            this.next.print();
        }
    }
}
