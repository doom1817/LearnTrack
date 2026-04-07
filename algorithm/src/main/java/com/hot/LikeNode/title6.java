package com.hot.LikeNode;

/**
 * @author: doom
 * @date: 2026/03/03/22:03
 * @description:
 * 力扣25. K 个一组翻转链表
 */
public class title6 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);
        System.out.println(reverseKGroup(node1, 2));
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next){
            n++;
        }
        ListNode cur = head;
        ListNode pre = null;
        //逐组翻转，n>=k 确保剩余节点足够 k 个
        for (;n>=k;n-=k){
            //头插法翻转当前组的 k 个节点
            for (int i = 0; i < k; i++){
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            //连接翻转后的子链表到原链表
            ListNode temp = p0.next;
            p0.next = pre;
            pre = temp;
            p0 = cur;
        }
        return dummy.next;
    }
}
