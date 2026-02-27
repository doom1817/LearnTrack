package com.designpattern.designFuctions.NoedLink;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/23/15:25
 * @Description:
 */
public interface List<E> {
    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(Object o);
    E get(int index);
    void printLinkList();
}
