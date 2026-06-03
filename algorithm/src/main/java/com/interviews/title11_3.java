package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/03/10:51
 * @description:
 *  力扣25. K 个一组翻转链表
 */
public class title11_3 {
    private static class ListNode{
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
        public ListNode next(int val) {
            this.next = new ListNode(val);
            return this.next;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                sb.append(cur.val);
                if (cur.next != null) {
                    sb.append("->");
                }
                cur = cur.next;
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next(2).next(3).next(4).next(5);
        System.out.println(reverseKGroup(node1,2));
    }
    private static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null){
            // 找到k个节点
            for (int i = 0; i < k && end != null; i++){
                end = end.next;
            }
            //终止条件：end为空
            if (end == null){
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            // 断开当前组与后续节点的连接,进行翻转
            end.next = null;
            pre.next = reverse(start);
            // 重新连接翻转后的组与后续节点
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }
    private static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
