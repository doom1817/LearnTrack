package com.hot.LikeNode;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @author: doom
 * @date: 2026/03/03/22:39
 * @description:
 * 力扣 148. 排序链表
 */
public class title7 {
    public static void main(String[] args) {
        // 创建测试链表：4 -> 2 -> 1 -> 3
        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(1);
        node1.next.next.next = new ListNode(3);
        // 调用排序方法并输出结果
        System.out.println(sortList(node1));
    }

    /**
     * 使用归并排序对链表进行升序排序
     * 主要思路：分治法
     * 1. 分解：使用快慢指针找到链表中点，将链表一分为二
     * 2. 解决：递归地对两个子链表分别排序
     * 3. 合并：将两个有序链表合并为一个有序链表
     */
    public static ListNode sortList(ListNode head) {
        // 边界条件：空链表或只有一个节点时直接返回
        if (head == null|| head.next == null) {return  head;}

        // 初始化快慢指针，slow 每次走一步，fast 每次走两步
        ListNode slow = head,fast = head.next;

        // 当 fast 到达末尾时，slow 正好在中间位置
        while (fast != null && fast.next != null){
            slow = slow.next;           // 慢指针前进一步
            fast = fast.next.next;      // 快指针前进两步
        }

        // rightHead 指向右半部分的头节点
        ListNode rightHead = slow.next;

        // 断开链表，将原链表分为左右两部分
        slow.next = null;

        // 递归排序左半部分链表
        ListNode left = sortList(head);

        // 递归排序右半部分链表
        ListNode right = sortList(rightHead);

        // 合并两个有序链表
        return merge(left,right);
    }

    /**
     * 合并两个升序链表
     * 使用双指针法，比较两个链表的当前节点值
     * 将较小的节点连接到结果链表中
     */
    private static ListNode merge(ListNode left,ListNode right){
        // 创建虚拟头节点，简化边界处理
        ListNode dummy = new ListNode(0);

        // tail 指向结果链表的尾部
        ListNode tail = dummy;

        // 遍历两个链表，直到其中一个为空
        while (left != null && right != null){
            // 比较两个节点的 val 值
            if (left.val < right.val){
                // left 较小，将 left 接入结果链表
                tail.next = left;
                // left 指针后移
                left = left.next;
            }else {
                // right 较小，将 right 接入结果链表
                tail.next = right;
                // right 指针后移
                right = right.next;
            }
            // tail 指针后移
            tail = tail.next;
        }

        // 将剩余的非空链表接入结果链表
        tail.next = left != null ? left : right;

        // 返回合并后的链表头节点
        return dummy.next;
    }
}
