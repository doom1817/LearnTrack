package com.interview.Collection队列;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * @author: doom
 * @date: 2026/06/08/20:08
 * @description:
 */
public class BlockingQueueThreadSafetyDemo {
    // 使用ArrayBlockingQueue演示线程安全
    static class ArrayQueueExample {
        private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        public void demonstrate() throws InterruptedException {
            // 生产者线程
            Thread producer1 = new Thread(() -> {
                try {
                    System.out.println("生产者1开始生产");
                    queue.put(1);
                    System.out.println("生产者1生产了1");
                    queue.put(2);
                    System.out.println("生产者1生产了2");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread producer2 = new Thread(() -> {
                try {
                    System.out.println("生产者2开始生产");
                    queue.put(3);
                    System.out.println("生产者2生产了3");
                    // 队列已满，这里会阻塞
                    queue.put(4);
                    System.out.println("生产者2生产了4");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            // 消费者线程
            Thread consumer = new Thread(() -> {
                try {
                    Thread.sleep(100); // 等待生产者先开始
                    System.out.println("消费者开始消费");
                    System.out.println("消费: " + queue.take());
                    Thread.sleep(200);
                    System.out.println("消费: " + queue.take());
                    Thread.sleep(200);
                    System.out.println("消费: " + queue.take());
                    Thread.sleep(200);
                    System.out.println("消费: " + queue.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            producer1.start();
            producer2.start();
            consumer.start();

            producer1.join();
            producer2.join();
            consumer.join();
        }
    }

    // 使用LinkedBlockingQueue演示线程安全
    static class LinkedQueueExample {
        private final BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

        public void demonstrate() throws InterruptedException {
            // 多个生产者-消费者测试
            Runnable producerTask = () -> {
                for (int i = 0; i < 3; i++) {
                    try {
                        String item = Thread.currentThread().getName() + "-item" + i;
                        queue.put(item); // 线程安全的put操作
                        System.out.println(Thread.currentThread().getName() + " 生产: " + item);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };

            Runnable consumerTask = () -> {
                for (int i = 0; i < 3; i++) {
                    try {
                        String item = queue.take(); // 线程安全的take操作
                        System.out.println(Thread.currentThread().getName() + " 消费: " + item);
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };

            // 创建多个线程
            Thread p1 = new Thread(producerTask, "生产者1");
            Thread p2 = new Thread(producerTask, "生产者2");
            Thread c1 = new Thread(consumerTask, "消费者1");
            Thread c2 = new Thread(consumerTask, "消费者2");

            p1.start();
            p2.start();
            c1.start();
            c2.start();

            p1.join();
            p2.join();
            c1.join();
            c2.join();
        }
    }

    // 对比非线程安全的PriorityQueue
    static class NonThreadSafeExample {
        private final java.util.PriorityQueue<Integer> unsafeQueue = new java.util.PriorityQueue<>();

        public void demonstrateUnsafe() {
            Runnable task = () -> {
                for (int i = 0; i < 1000; i++) {
                    unsafeQueue.add(i); // 非线程安全的操作
                }
            };

            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
                System.out.println("非线程安全队列大小: " + unsafeQueue.size());
                // 可能抛出ConcurrentModificationException或结果不正确
            } catch (Exception e) {
                System.out.println("发生并发异常: " + e.getClass().getSimpleName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ArrayBlockingQueue 线程安全演示 ===");
        new ArrayQueueExample().demonstrate();

        System.out.println("\n=== LinkedBlockingQueue 线程安全演示 ===");
        new LinkedQueueExample().demonstrate();

        System.out.println("\n=== PriorityQueue 非线程安全对比 ===");
        new NonThreadSafeExample().demonstrateUnsafe();

        // 证明阻塞队列的原子操作
        System.out.println("\n=== 阻塞队列原子操作演示 ===");
        BlockingQueue<Integer> atomicQueue = new ArrayBlockingQueue<>(1);
        atomicQueue.put(100);

        Thread offerThread = new Thread(() -> {
            try {
                boolean offered = atomicQueue.offer(200, 1, TimeUnit.SECONDS);
                System.out.println("offer操作结果: " + offered); // 应该为false，因为队列已满
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        offerThread.start();
        Thread.sleep(200); // 确保offer线程执行
        System.out.println("队列当前元素: " + atomicQueue.poll()); // 取出100
        offerThread.join();
        System.out.println("offer操作后队列大小: " + atomicQueue.size()); // 应该为0，因为offer失败
    }
}
