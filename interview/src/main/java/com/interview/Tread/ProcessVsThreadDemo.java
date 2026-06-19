package com.interview.Tread;

import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: doom
 * @date: 2026/06/19/14:25
 * @description:
 *  Process和Thread的区别
 *  演示了：
 *  1.线程共享内存：多个线程操作同一个对象，数据实时互通（但也展示了竞态条件）。
 *  2.进程隔离内存：启动子进程修改数据，父进程的数据完全不受影响。
 */
public class ProcessVsThreadDemo  {
    // 共享计数器，用于演示线程间的内存共享
    private static final AtomicInteger sharedCounter = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        System.out.println("========== 1. 线程模拟：内存共享 ==========");
        demonstrateThreads();

        System.out.println("\n========== 2. 进程模拟：内存隔离 ==========");
        demonstrateProcesses();
    }

    /**
     * 演示线程：多个线程共享同一个 JVM 堆内存
     */
    private static void demonstrateThreads() throws InterruptedException {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 5; i++) {
                int val = sharedCounter.incrementAndGet();
                System.out.printf("[%s] 看到共享计数器值: %d%n", threadName, val);
                try { Thread.sleep(100); } catch (InterruptedException e) { break; }
            }
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        System.out.println("初始计数器值: " + sharedCounter.get());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("最终计数器值: " + sharedCounter.get());
        System.out.println("✅ 结论: 线程A和线程B操作的是【同一份】内存数据");
    }

    /**
     * 演示进程：子进程拥有独立的 JVM 和内存空间
     */
    private static void demonstrateProcesses() throws Exception {
        System.out.println("父进程初始计数器值: " + sharedCounter.get());

        // 启动一个全新的 JVM 进程（子进程）
        ProcessBuilder pb = new ProcessBuilder(
                "java", "-cp", System.getProperty("java.class.path"),
                "ProcessVsThreadDemo$ChildProcess"
        );
        pb.inheritIO(); // 继承父进程的 IO，方便观察输出
        Process child = pb.start();

        // 父进程同时也在修改自己的计数器
        for (int i = 0; i < 5; i++) {
            int val = sharedCounter.incrementAndGet();
            System.out.printf("[Parent-Process] 自己的计数器值: %d%n", val);
            Thread.sleep(100);
        }

        child.waitFor();
        System.out.println("父进程最终计数器值: " + sharedCounter.get());
        System.out.println("✅ 结论: 子进程的修改对父进程【完全不可见】，内存是隔离的");
    }

    /**
     * 内部类作为子进程的入口点
     * 注意：虽然写在同一个文件中，但通过 ProcessBuilder 启动时
     * 它是一个全新的 JVM 实例，static 变量会被重新初始化
     */
    public static class ChildProcess {
        public static void main(String[] args) throws InterruptedException {
            // 子进程有自己的 sharedCounter，初始值为 0
            System.out.println("[Child-Process] 我的计数器初始值: " + sharedCounter.get());
            for (int i = 0; i < 5; i++) {
                int val = sharedCounter.incrementAndGet();
                System.out.printf("[Child-Process] 我的计数器值: %d%n", val);
                Thread.sleep(150);
            }
            System.out.println("[Child-Process] 我的计数器最终值: " + sharedCounter.get());
        }
    }

}
