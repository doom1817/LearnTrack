package com.designFuctions.NoedLink;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/23/16:12
 * @Description:
 */
public class DoublyCircleLinkedList<E> implements List<E> {
    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
    private Node<E> head; // 头节点
    private int size;
    public DoublyCircleLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        return addLast(e);
    }

    @Override
    public boolean addFirst(E e) {
        Node<E> newNode = new Node<>(e, null, null);
        if (head == null) {
            // 空链表, 新节点自循环
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        }else {
            //新节点插入到头节点之前
            newNode.next = head;
            newNode.prev = head.prev; // 尾节点
            head.prev.next = newNode; // 尾节点的next指向新节点
            head.prev = newNode;      // 头节点的prev指向新节点
            head = newNode;           // 新节点成为头节点
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        Node<E> newNode = new Node<>(e, null, null);
        if (head == null) {
            // 空链表：新节点自循环
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        }
        else {
            // 新节点插入到尾节点之后
            newNode.prev = head.prev; // 尾节点
            newNode.next = head;      // 头节点
            head.prev.next = newNode; // 尾节点的next指向新节点
            head.prev = newNode;      // 尾节点更新为新节点
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null)return false;
        Node<E> current = head;
        do {
            if (Objects.equals(current.data, o)){
                // 删除节点
                if (current == head){
                    head = head.next;
                }
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return true;
            }
            current = current.next;
        }while (current != head);
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void printLinkList() {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head;
        do {
            sb.append(current.data);
            current = current.next;
            if (current != head) {
                sb.append(", ");
            }
        } while (current != head);
        sb.append("]");
        System.out.println(sb.toString());
    }
}
