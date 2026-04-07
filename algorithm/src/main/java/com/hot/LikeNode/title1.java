package com.hot.LikeNode;

/**
 * @author: doom
 * @date: 2026/03/03/19:54
 * @description:
 * 力扣160. 相交链表
 */
public class title1 {
    public static void main(String[] args) {
        // 测试用例 1: 两个链表相交
        testIntersectingLists();

//        // 测试用例 2: 两个链表不相交
//        testNonIntersectingLists();
//
//        // 测试用例 3: 其中一个链表为空
//        testWithEmptyList();
    }
    /**
     * 测试两个链表相交的情况
     */
    private static void testIntersectingLists() {
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = headA.next.next.next; // 相交点 8
        ListNode intersectionNode = getIntersectionNode(headA, headB);
        System.out.println("相交节点的值为：" + intersectionNode.val);
    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 如果两个链表不相交，则两个指针最终会同时为 null
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }
}
