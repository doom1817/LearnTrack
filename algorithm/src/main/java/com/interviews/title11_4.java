package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/05/09:57
 * @description:
 *  力扣82. 删除排序链表中的重复元素Ⅱ
 */
public class title11_4 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode next(int val){
            this.next = new ListNode(val);
            return this.next;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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
    public static  ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.next != null && prev.next.val == prev.next.next.val) {
                int duplicateVal = prev.next.val; // 记录重复数字
                // 删除重复数字
                while (prev.next != null && prev.next.val == duplicateVal) {
                    prev.next = prev.next.next;
                }
            } else {
                prev = prev.next; // 移动到下一个节点
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next(2).next(3).next(3).next(4).next(4).next(5);
        System.out.println(deleteDuplicates(root)); // 1->2->5
    }

}
