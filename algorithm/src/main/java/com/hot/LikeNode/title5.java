package com.hot.LikeNode;

/**
 * @author: doom
 * @date: 2026/03/03/21:51
 * @description:
 * 力扣24. 两两交换链表中的节点
 */
public class title5 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        System.out.println(swapPairs(node1));
    }
    public static ListNode swapPairs(ListNode head) {
        // 终止条件：如果链表为空，或者只剩一个节点（奇数长度的最后一种情况）
        if (head == null||head.next == null){return head;}
        ListNode first = head; // 第一个节点
        ListNode second = head.next; // 第二个节点
        ListNode rest  =second.next; // 剩余节点
        first.next = swapPairs(rest);
        second.next = first; // 翻转就是直接进行两两交换
        return second;
    }
}
