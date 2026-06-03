package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/03/09:34
 * @description: 力扣 21.合并两个有序链表
 */
public class title11_1 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode next(int val) {
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
        node1.next(2).next(4);  // 忽略返回值，node1 依然是头节点
        ListNode node2 = new ListNode(1);
        node2.next(3).next(4);
        System.out.println(mergeTwoLists(node1, node2));
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1); // 虚拟头节点
        ListNode cur = dummy;
        while(list1 != null  && list2 !=null){
            if(list1.val <= list2.val){
                cur.next =list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = (list1 != null) ? list1:list2;
        return dummy.next;
    }
}
