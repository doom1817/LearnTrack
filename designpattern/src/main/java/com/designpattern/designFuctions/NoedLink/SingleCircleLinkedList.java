package com.designpattern.designFuctions.NoedLink;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : doom
 * @date: 2026/02/23/16:12
 * @description:
 */
public class SingleCircleLinkedList<E> implements List<E>{
    private static class Node<E>{
        E data;
        Node<E> next;
        public Node(E data){
            this.data = data;
        }
    }
    private Node<E> tail; // 指向尾节点
    private int size; // 链表大小

    public SingleCircleLinkedList(){
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        return addLast(e);
    }

    @Override
    public boolean addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null){
            //空链表
            tail = newNode;
            tail.next = tail; // 指向自己
        }else{
            // 新节点的next指向原头节点（tail.next）
            newNode.next = tail.next;
            // 尾节点的next指向新节点
            tail.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null){
            tail = newNode;
            tail.next = tail;
        }else{
            // 新节点的next指向原头节点（tail.next）
            newNode.next = tail.next;
            // 尾节点的next指向新节点
            tail.next = newNode;
            // 更新tail为新节点
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (tail == null) return false;

        Node<E> current = tail;  // 从尾节点开始遍历
        Node<E> prev = null;
        boolean found= false;
        do{
            prev = current;
            current = current.next;
            if (current.data.equals(o)){
                found = true;
                break;
            }
        }while (current != tail);

        if (found){
            // 如果删除的是尾节点（current == tail）
            if (current == tail){
                if (tail ==tail.next){
                    // 只有一个节点
                    tail = null;
                }else {
                    // 更新tail为前驱节点
                    tail = prev;
                    prev.next = tail.next;  // 尾节点的前驱指向头节点
                }
            }else if (current == tail.next){
                // 删除头节点
                tail.next = current.next;
            }else {
                // 删除中间节点
                prev.next = current.next;
            }
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
        Node<E> current = tail.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void printLinkList() {
        if (tail == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = tail.next;  // 头节点
        do {
            sb.append(current.data);
            current = current.next;
            if (current != tail.next) {
                sb.append(", ");
            }
        } while (current != tail.next);
        sb.append("]");
        System.out.println(sb.toString());
    }
}
