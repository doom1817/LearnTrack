package com.hot.LikeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: doom
 * @date: 2026/03/03/20:12
 * @description:
 * 力扣234. 回文链表
 */
public class title3 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(2);
        node1.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(node1));
    }
    protected static boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head); // 中间节点
        ListNode head2 = reverseList(mid); // 反转后半部分
        while (head2 != null){
            if (head.val != head2.val){
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }
    private static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    private static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
