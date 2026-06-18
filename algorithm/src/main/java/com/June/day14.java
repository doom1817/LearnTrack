package com.June;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/06/14/15:02
 * @description:
 *  力扣2130.链表最大孪生和
 */
public class day14 {
    private static class  ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
        ListNode next(int val){
            this.next = new ListNode(val);
            return this.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        node1.next(4).next(2).next(1);
        System.out.println(pairSum(node1));
    }

    private static  int pairSum(ListNode head){
        //1.快慢指针找到中点
        ListNode slow = head,fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //2.反转后半部分链表
        ListNode pre = null;
        ListNode cur = slow;
        while (cur!=null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        //3.遍历前后两部分求和
        int ans = 0;
        ListNode first = head,second = pre;
        while (second != null){
            ans = Math.max(ans,first.val+second.val);
            first = first.next;
            second = second.next;
        }
        return ans;
    }
}
