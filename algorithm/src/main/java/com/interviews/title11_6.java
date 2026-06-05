package com.interviews;

import java.util.List;

/**
 * @author: doom
 * @date: 2026/06/05/11:12
 * @description:
 *  力扣86.分隔链表
 */
public class title11_6 {
    private static  class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode next(int val){
            this.next = new ListNode(val);
            return this.next;
        }
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){ this.val = val; this.next = next;}
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                sb.append(cur.val);
                sb.append("->");
                cur = cur.next;
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next(4).next(3).next(2).next(5).next(2);
        System.out.println(partition(node, 3));//1->2->2->4->3->5
    }
    private static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode smallHead = small;
        ListNode large = new ListNode();
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x){
                small.next = head;
                small = small.next;
            }else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        small.next = largeHead.next;
        large.next = null;
        return smallHead.next;
    }
}
