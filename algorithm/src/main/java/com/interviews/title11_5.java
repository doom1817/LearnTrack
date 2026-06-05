package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/05/09:57
 * @description:
 *  力扣61. 旋转链表
 */
public class title11_5 {
    private  static  class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode next(int val){
            this.next = new ListNode(val);
            return this.next;
        }
    }
    private static ListNode rotateRight(ListNode head, int k) {
        //边界判断
        if (head == null || head.next == null|| k == 0) {
            return head;
        }
        //1.求链表长度,定位尾节点
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        //2.实际需要旋转的步数
        k%=len;
        if (k == 0){
            return  head;
        }
        //3.首尾相连
        tail.next = head;
        //4.找到新尾节点
        int newTail = len - k;
        ListNode newTailNode = head;
        for (int i = 0; i < newTail; i++){
            newTailNode = newTailNode.next;
        }
        //5.断开环,重新确定头节点
        ListNode newHead = newTailNode.next;
        newTailNode.next = null;
        return newHead;
    }
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next(2).next(3).next(4).next(5);
        System.out.println(rotateRight(node, 2));
    }
}
