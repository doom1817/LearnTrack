package com.interview.Tread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.Duration;
/**
 * @author: doom
 * @date: 2026/06/19/14:45
 * @description:
 * 创建 10万个 并发任务，传统 OS 线程会直接撑爆内存或耗时极长，而基于用户线程实现的虚拟线程则能轻松拿捏。
 * Java 的线程模型经历了一个“轮回”，完美诠释了这两种线程的博弈：
 * JDK 1.0 ~ 1.2 (N:1 用户线程)：
 * 使用 Green Threads，由于无法利用多核且 I/O 阻塞问题严重，被废弃。
 * JDK 1.3 ~ JDK 20 (1:1 操作系统线程)：
 * 使用 Platform Threads，一个 Java Thread 严格绑定一个 OS 线程。稳定、能用多核，但在高并发 I/O 场景下（如 Web 服务器），创建几万个线程就会导致内存耗尽（OOM）或 CPU 频繁上下文切换。
 * JDK 21+ (N:M 混合模型)：
 * 引入 虚拟线程（Virtual Threads）。它本质上是用户线程，由 JVM 调度。JVM 维护少量的 OS 线程（称为载体线程 Carrier Threads），成千上万的虚拟线程在这些载体线程上复用运行。
 */
public class ThreadModelDemo {
    public static void main(String[] args) throws Exception {
        int TASK_COUNT = 100_000; // 10万个并发任务

        System.out.println("========== 1. 传统平台线程 (1:1 操作系统线程) ==========");
        // 注意：如果直接 new Thread 10万个，通常会直接抛出 OutOfMemoryError
        // 这里我们使用线程池，限制最大 OS 线程数为 200，模拟传统 Web 服务器模型
        testPlatformThreads(TASK_COUNT, 200);

        System.out.println("\n========== 2. 虚拟线程 (N:M 用户态线程) ==========");
        // Java 21+ 特性：每个任务一个虚拟线程，轻松创建 10万个
        testVirtualThreads(TASK_COUNT);
    }

    /**
     * 传统模型：线程池受限，任务排队
     */
    private static void testPlatformThreads(int count, int poolSize) throws InterruptedException {
        AtomicInteger completed = new AtomicInteger(0);
        long start = System.currentTimeMillis();

        // 创建固定大小的线程池（底层是 OS 线程）
        try (ExecutorService executor = Executors.newFixedThreadPool(poolSize)) {
            for (int i = 0; i < count; i++) {
                executor.submit(() -> {
                    simulateBlockingIO(); // 模拟 I/O 阻塞
                    completed.incrementAndGet();
                });
            }
        } // AutoCloseable 会等待所有任务完成

        long duration = System.currentTimeMillis() - start;
        System.out.printf("✅ 完成 %d 个任务，耗时: %d ms (受限于 OS 线程数量，大量时间在排队)%n",
                completed.get(), duration);
    }

    /**
     * 现代模型：虚拟线程，海量并发
     */
    private static void testVirtualThreads(int count) throws InterruptedException {
        AtomicInteger completed = new AtomicInteger(0);
        long start = System.currentTimeMillis();

        // Java 21+：创建虚拟线程执行器（底层是 JVM 调度的用户线程）
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < count; i++) {
                executor.submit(() -> {
                    simulateBlockingIO(); // 遇到阻塞时，虚拟线程会自动让出载体线程
                    completed.incrementAndGet();
                });
            }
        }

        long duration = System.currentTimeMillis() - start;
        System.out.printf("✅ 完成 %d 个任务，耗时: %d ms (10万个用户线程同时运行，无需排队)%n",
                completed.get(), duration);
    }

    // 模拟耗时的 I/O 操作（如查数据库、调外部API）
    private static void simulateBlockingIO() {
        try {
            Thread.sleep(Duration.ofMillis(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
