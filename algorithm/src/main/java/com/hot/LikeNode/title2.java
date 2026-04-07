package com.hot.LikeNode;

/**
 * @author: doom
 * @date: 2026/03/03/20:07
 * @description:
 * 力扣206. 反转链表
 */
public class title2 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(node1);
        ListNode node = reverseList(node1);
        System.out.println(node);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre;
        if (head == null||head.next == null)return head;
        pre = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return pre;
    }
}
