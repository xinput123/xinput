package com.xx;

import com.xinput.model.ListNode;
import org.junit.Test;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @since
 */
public class Demo {

    @Test
    public void test1() {
        ListNode listNode1 = new ListNode(0);
        listNode1.add(new ListNode(2));
        listNode1.add(new ListNode(4));
        listNode1.add(new ListNode(6));
        listNode1.add(new ListNode(8));
        System.out.print("listNode1 : ");
        listNode1.print();
    }

}
