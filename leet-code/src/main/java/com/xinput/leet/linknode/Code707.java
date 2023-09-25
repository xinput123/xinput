package com.xinput.leet.linknode;

import com.xinput.model.ArrayListNode;

/**
 * https://leetcode.cn/problems/design-linked-list/
 * 707. 设计链表
 */
public class Code707 {

    public static void main(String[] args) {
        ArrayListNode list = new ArrayListNode();
        list.addAtHead(1);
        list.print();
        System.out.println(list.get(0));

        list.addAtTail(3);
        list.print();

        list.addAtIndex(1, 2);
        list.print();
        System.out.println(list.get(1));

        list.deleteAtIndex(1);
        list.print();
        System.out.println(list.get(1));
    }


}