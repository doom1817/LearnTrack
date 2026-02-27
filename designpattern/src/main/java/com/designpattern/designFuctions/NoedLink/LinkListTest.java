package com.designpattern.designFuctions.NoedLink;



/**
 * Created with IntelliJ IDEA.
 *
 * @author : doom
 * @date: 2026/02/23/16:12
 * @description:
 */
public class LinkListTest {
    public static void main(String[] args) {
//        DoublyLinkedListTest();
//        SinglyLinkedListTest;
//        SingleCircleLinkedListTest();
        DoublyCircleLinkedListTest();
    }
    /**
     * 测试双向链表
     */
    private static void DoublyLinkedListTest() {
        System.out.println("=== 双向链表测试 ===");
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        // 1. 测试 add / addLast（尾部添加）
        list.add("Apple");      // 等价于 addLast
        list.addLast("Banana");
        System.out.print("添加 Apple, Banana: ");
        list.printLinkList();   // 预期: [Apple, Banana]

        // 2. 测试 addFirst（头部添加）
        list.addFirst("Cherry");
        System.out.print("头部添加 Cherry: ");
        list.printLinkList();   // 预期: [Cherry, Apple, Banana]

        // 3. 测试 get
        System.out.println("索引 1 的元素: " + list.get(1)); // 预期: Apple
        // list.get(10); // 应抛出 IndexOutOfBoundsException

        // 4. 测试 remove
        list.remove("Apple");
        System.out.print("删除 Apple 后: ");
        list.printLinkList();   // 预期: [Cherry, Banana]

        // 5. 边界测试：空链表、重复元素、null 值（可选）
        list.remove("Cherry");
        list.remove("Banana");
        System.out.print("清空后: ");
        list.printLinkList();   // 预期: []

        // 6. 重新添加并验证顺序
        list.addFirst("First");
        list.addLast("Last");
        list.add("Middle"); // add 默认是 addLast
        System.out.print("最终链表: ");
        list.printLinkList(); // 预期: [First, Middle, Last]

        System.out.println();
    }

    /**
     * 测试单链表
     */

    private static void SinglyLinkedListTest() {
        System.out.println("=== 单链表测试 ===");
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.add("Apple");      // 尾插
        list.addLast("Banana");
        System.out.print("添加 Apple, Banana: ");
        list.printLinkList();   // [Apple, Banana]

        list.addFirst("Cherry");
        System.out.print("头部添加 Cherry: ");
        list.printLinkList();   // [Cherry, Apple, Banana]

        System.out.println("索引 1 的元素: " + list.get(1)); // Apple

        list.remove("Apple");
        System.out.print("删除 Apple 后: ");
        list.printLinkList();   // [Cherry, Banana]

        list.remove("Cherry");
        list.remove("Banana");
        System.out.print("清空后: ");
        list.printLinkList();   // []

        list.add("X");
        list.addFirst("Y");
        System.out.print("最终链表: ");
        list.printLinkList();   // [Y, X]

        System.out.println();

    }

    /**
     *  测试单向循环链表
     */
    private static void SingleCircleLinkedListTest() {
        System.out.println("=== 单向循环链表测试 ===");
        SingleCircleLinkedList<String> list = new SingleCircleLinkedList<>();

        // 1. 测试 add / addLast（尾部添加）
        list.add("Apple");      // 等价于 addLast
        list.addLast("Banana");
        System.out.print("添加 Apple, Banana: ");
        list.printLinkList();   // 预期: [Apple, Banana]

        // 2. 测试 addFirst（头部添加）
        list.addFirst("Cherry");
        System.out.print("头部添加 Cherry: ");
        list.printLinkList();   // 预期: [Cherry, Apple, Banana]

        // 3. 测试 get
        System.out.println("索引 1 的元素: " + list.get(1)); // 预期: Apple

        // 4. 测试 remove
        list.remove("Apple");
        System.out.print("删除 Apple 后: ");
        list.printLinkList();   // 预期: [Cherry, Banana]

        // 5. 边界测试：空链表、重复元素、null 值（可选）
        list.remove("Cherry");
        list.remove("Banana");
        System.out.print("清空后: ");
        list.printLinkList();   // 预期: []

        // 6. 重新添加并验证顺序
        list.addFirst("First");
        list.addLast("Last");
        list.add("Middle"); // add 默认是 addLast
        System.out.print("最终链表: ");
        list.printLinkList(); // 预期: [First, Middle, Last]

        System.out.println();
    }
    /**
     * 测试双向循环链表
     */
    private static void DoublyCircleLinkedListTest() {
        System.out.println("=== 双向循环链表测试 ===");
        DoublyCircleLinkedList<String> list = new DoublyCircleLinkedList<>();

        // 1. 测试 add / addLast（尾部添加）
        list.add("Apple");      // 等价于 addLast
        list.addLast("Banana");
        System.out.print("添加 Apple, Banana: ");
        list.printLinkList();   // 预期: [Apple, Banana]

        // 2. 测试 addFirst（头部添加）
        list.addFirst("Cherry");
        System.out.print("头部添加 Cherry: ");
        list.printLinkList();   // 预期: [Cherry, Apple, Banana]

        // 3. 测试 get
        System.out.println("索引 1 的元素: " + list.get(1)); // 预期: Apple

        // 4. 测试 remove
        list.remove("Apple");
        System.out.print("删除 Apple 后: ");
        list.printLinkList();   // 预期: [Cherry, Banana]

        // 5. 边界测试：空链表、重复元素、null 值（可选）
        list.remove("Cherry");
        list.remove("Banana");
        System.out.print("清空后: ");
        list.printLinkList();   // 预期: []

        // 6. 重新添加并验证顺序
        list.addFirst("First");
        list.addLast("Last");
        list.add("Middle"); // add 默认是 addLast
        System.out.print("最终链表: ");
        list.printLinkList(); // 预期: [First, Middle, Last]

        System.out.println();
    }

}
