package com.hot.LikeNode;

/**
 * @author: doom
 * @date: 2026/03/03/20:00
 * @description:
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null){
                sb.append(",");
            }
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
