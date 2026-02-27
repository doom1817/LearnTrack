package com.designFuctions.NoedLink;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/23/15:27
 * @Description:
 */
public class DoublyLinkedList<E> implements List<E>{
    private  Node<E> first;
    private  Node<E> last;
    private  int size = 0;

    public DoublyLinkedList() {
    }


    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        linkFirst(e);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        //统一处理null和非null情况
        for (Node<E> x = first; x != null; x = x.next){
            if ((o == null && x.item == null) || (o!=null && x.item.equals(o))){
                unlink(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = first;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;
    }

    @Override
    public void printLinkList() {
        Node<E> current = first;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.item);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    /**
     * 链表头
     */
    void linkFirst(E e){
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(e, f, null);
        if (f == null){
            last = newNode; // 如果原链表为空，则新节点既是头也是尾
        }else {
            f.prev = newNode;
        }
        first=newNode;
        size++;
    }
    /**
     * 链表尾
     */
    void linkLast(E e){
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, null, l);
        if (l == null){
            first = newNode;// 如果原链表为空，则新节点既是头也是尾
        }else{
            l.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * 拆链操作
     */
    E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        // 断开前置节点连接
        if (prev == null) {
            first = next; // x 是头节点
        } else {
            prev.next = next;
            x.prev = null; // 置空前驱引用
        }

        // 断开后置节点连接
        if (next == null) {
            last = prev; // x 是尾节点
        } else {
            next.prev = prev;
            x.next = null; // 置空后继引用
        }

        x.item = null; // 清理节点数据
        size--;
        return element;
    }


    // 链表节点
    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;
        public Node(E item, Node<E> next, Node<E> prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }


}
