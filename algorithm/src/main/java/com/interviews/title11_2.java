package com.interviews;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/06/03/10:24
 * @description:
 *  力扣92. 反转链表 II
 */
public class title11_2 {
    private static   class ListNode {
        int val;
        ListNode next;
        public ListNode() {}
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    private static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right|| head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        // 找到left的前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;// left节点
        // 开始反转
        for (int i = 0; i < right - left; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }
}
