package com.interview.container.ConcurrentContainers;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: doom
 * @date: 2026/05/21/11:57
 * @description:
 *  并发容器下的高并发哈希表
 *  特点：1.读写操作都支持高并发，读操作几乎无锁（JDK 8+ 使用 CAS + synchronized 锁住链表头节点）。
 *      2.迭代器是弱一致性的：遍历时不会抛出 ConcurrentModificationException，但可能不会看到迭代开始后的修改。
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // 写线程
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map.put("key" + i, i);
                System.out.println("写线程添加: key" + i);
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        });

        // 读线程（遍历）
        Thread reader = new Thread(() -> {
            while (true) {
                // 弱一致性：遍历过程中不会抛异常，且能实时看到已写入的部分数据
                for (String key : map.keySet()) {
                    System.out.println("读线程看到: " + key + " -> " + map.get(key));
                }
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        writer.start();
        reader.start();
        Thread.sleep(2000);
        writer.interrupt();
        reader.interrupt();
    }
}
