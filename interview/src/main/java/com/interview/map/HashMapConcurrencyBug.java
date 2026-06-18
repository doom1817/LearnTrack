package com.interview.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: doom
 * @date: 2026/06/16/09:31
 * @description: 这段代码演示 HashMap 的线程安全问题：
 * 制造冲突：自定义 Key 让所有元素 hash 到同一桶
 * 并发写入：8个线程同时执行 8万次 put 操作
 * 验证结果：对比预期大小和实际大小，展示数据丢失现象
 */
public class HashMapConcurrencyBug {
    // ⚠️ 关键：自定义Key，强制所有key都hash到同一个桶，制造极端冲突
    static class CollisionKey {
        private final int value;

        CollisionKey(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 1; // 所有key返回相同hashCode，强制落入同一桶
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof CollisionKey && ((CollisionKey) obj).value == this.value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 使用小容量HashMap，配合全碰撞Key，确保所有写入都在同一个桶内竞争
        Map<CollisionKey, Integer> map = new HashMap<>(4);
        int threadCount = 8;
        int putsPerThread = 10_000;
        int expectedSize = threadCount * putsPerThread; // 预期 80,000

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1); // 确保所有线程同时开始
        CountDownLatch endLatch = new CountDownLatch(threadCount);
        AtomicInteger actualPutCount = new AtomicInteger(0);

        for (int t = 0; t < threadCount; t++) {
            final int threadId = t;
            executor.submit(() -> {
                try {
                    startLatch.await(); // 等待统一发令枪
                    for (int i = 0; i < putsPerThread; i++) {
                        CollisionKey key = new CollisionKey(threadId * putsPerThread + i);
                        map.put(key, threadId);
                        actualPutCount.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            });
        }

        // 同时释放所有线程，最大化并发冲突窗口
        startLatch.countDown();
        endLatch.await();
        executor.shutdown();

        // ========== 验证结果 ==========
        System.out.println("========== JDK 1.8+ HashMap 并发问题复现结果 ==========");
        System.out.println("预期元素数量: " + expectedSize);
        System.out.println("实际 put 调用次数: " + actualPutCount.get());
        System.out.println("map.size() 返回值: " + map.size());
        System.out.println("遍历实际元素个数: " + countByIteration(map));

        if (map.size() != expectedSize) {
            System.out.printf("%n❌ 数据丢失! 丢失 %d 条记录 (%.2f%%)%n",
                    expectedSize - map.size(),
                    (expectedSize - map.size()) * 100.0 / expectedSize);
        } else {
            System.out.println("\n✅ 本次未观察到丢失 (多试几次或增加线程数)");
        }
    }

    // size() 本身也可能不准，用遍历计数作为真实基准
    private static int countByIteration(Map<?, ?> map) {
        int count = 0;
        for (Object ignored : map.entrySet()) count++;
        return count;
    }
}

