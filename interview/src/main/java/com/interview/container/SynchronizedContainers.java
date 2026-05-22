package com.interview.container;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/05/21/11:51
 * @description:
 * 同步容器 :
 * 核心机制是全局锁：几乎所有方法都直接用 synchronized 修饰，锁住整个对象。
 * 特点：实现简单，但但并发性能差，同一时刻只有一个线程能执行操作。
 */
public class SynchronizedContainers {
    /*
     * 通过 synchronized 关键字将所有方法包装成一个全局锁（锁住当前对象）。
     * 性能较差，并发写入/读取都会串行化。
     * 重要坑点：使用迭代器遍历时必须手动同步整个集合，否则会抛出 ConcurrentModificationException。
     */
    public static void main(String[] args) throws InterruptedException {
        //创建一个普通的 HashSet，再包装成同步 Set
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");

        //错误示例：迭代器遍历时未手动同步
        Thread badIterator = new Thread(() -> {
            for (String s : set){  //错误：ConcurrentModificationException
                System.out.println(s);
            }
        });
        //正确做法：手动同步
        Thread goodIterator = new Thread(() -> {
            synchronized (set) {
                for (String s : set){
                    System.out.println(s);
                }
            }
        });
        //同时进行修改操作
        Thread modifier = new Thread(() -> {
            for (int i = 0; i < 5; i++){
                set.add("X" + i);
                System.out.println("添加了 X" + i);
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        });
        goodIterator.start();
        modifier.start();
        goodIterator.join();
        modifier.join();
    }
}
