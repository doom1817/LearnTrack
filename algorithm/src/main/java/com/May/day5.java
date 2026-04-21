package com.May;

import org.w3c.dom.ls.LSOutput;

/**
 * @author: doom
 * @date: 2026/05/05/20:53
 * @description: 力扣61. 旋转链表
 */
public class day5 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(listToString(rotateRight(head, 2))); // [4,5,1,2,3]
    }

    public static ListNode rotateRight(ListNode head, int k) {
        // 空链表 / 单节点 / 旋转0步，直接返回
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. 求链表长度，同时定位到尾节点
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. 实际需要旋转的步数
        k %= length;
        if (k == 0) {
            return head;          // 等效于不旋转，直接返回
        }

        // 3. 首尾相连，形成环
        tail.next = head;

        // 4. 找到新尾节点：原链表的第 (length - k) 个节点（从1开始计数）
        int newTailIndex = length - k;
        ListNode newTail = head;
        for (int i = 1; i < newTailIndex; i++) { // 移动 newTailIndex-1 步
            newTail = newTail.next;
        }

        // 5. 断开环，重新确定头节点
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    private static class ListNode {
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

    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append(",");
            }
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
