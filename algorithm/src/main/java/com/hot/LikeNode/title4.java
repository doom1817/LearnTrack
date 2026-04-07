package com.hot.LikeNode;

/**
 * @author: doom
 * @date: 2026/03/03/20:41
 * @description:
 * 力扣19. 删除链表的倒数第 N 个结点
 */
public class title4 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode node = removeNthFromEnd(l1, 2);
        System.out.println(node);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null||head.next == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i <= n; i++){
            fast = fast.next;
        }
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; // 删除 跳过
        return dummy.next;
    }


}
