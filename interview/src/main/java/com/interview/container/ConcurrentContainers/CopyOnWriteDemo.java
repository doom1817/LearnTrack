package com.interview.container.ConcurrentContainers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: doom
 * @date: 2026/05/21/11:58
 * @description:  review 读多写少场景
 * 特点：
 * 1.所有修改操作（add、set、remove）都会复制整个底层数组，并使用 ReentrantLock 保证只有一个线程执行复制。
 * 2.读操作完全无锁，性能极高，读的时候永远不会阻塞。*
 * 3.迭代器是快照风格的：迭代器创建后，其他线程的修改不会影响它，也不会抛出 ConcurrentModificationException。
 */
public class CopyOnWriteDemo {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new CopyOnWriteArrayList<>();

        // 初始化一些数据
        list.add("A");
        list.add("B");

        // 读线程：使用迭代器
        Thread reader = new Thread(() -> {
            for (String s : list) {   // 迭代器创建时复制了当前数组快照
                System.out.println("读线程迭代元素: " + s);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        // 写线程：修改 list
        Thread writer = new Thread(() -> {
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            list.add("C");
            System.out.println("写线程添加了 C");
            list.remove("A");
            System.out.println("写线程删除了 A");
        });

        reader.start();
        writer.start();

        reader.join();
        writer.join();

        // 最终 list 内容为 ["B", "C"] 因为写操作已经生效
        System.out.println("最终列表: " + list);
    }
}
