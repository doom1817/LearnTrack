package com.designpattern.designFuctions.NoedLink;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : doom
 * @date: 2026/02/23/16:12
 * @description:
 */
public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; }
    }

    private Node<E> head;
    private int size;

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null) return false;

        // 删除头节点
        if (Objects.equals(head.data, o)) {
            head = head.next;
            size--;
            return true;
        }

        // 删除中间/尾节点
        Node<E> prev = head;
        while (prev.next != null && !Objects.equals(prev.next.data, o)) {
            prev = prev.next;
        }

        if (prev.next != null) {
            prev.next = prev.next.next;
            size--;
            return true;
        }

        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void printLinkList() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        System.out.println(sb);
    }
}
