package com.xinput.util;

import com.xinput.model.ListNode;

import java.util.List;

public class NodeUtils {

    public static ListNode create(List<Integer> lists) {
        if (lists == null || lists.size() == 0) {
            return new ListNode();
        }

        ListNode listNode = new ListNode(lists.get(0));
        for (int i = 1; i < lists.size(); i++) {
            listNode.add(new ListNode(lists.get(i)));
        }
        return listNode;
    }
}
