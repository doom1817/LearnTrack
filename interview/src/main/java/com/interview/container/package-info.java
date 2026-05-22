/**
 * @author: doom
 * @date: 2026/05/21/11:57
 * @description:
 * <h2>同步容器与并发容器</h2>
 *
 * <h3>一、同步容器（Synchronized Containers）</h3>
 * <p>对应类：{@link com.interview.container.SynchronizedContainers}</p>
 * <ul>
 *   <li><b>核心机制</b>：全局锁。几乎所有方法都用 {@code synchronized} 修饰，锁住当前对象实例。</li>
 *   <li><b>常见类型</b>：{@code Vector}、{@code Hashtable}、以及 {@code Collections.synchronizedList()} / {@code synchronizedSet()} /
 *       {@code synchronizedMap()} 包装出的同步视图。</li>
 *   <li><b>具体表现</b>：
 *     <ol>
 *       <li><b>读写全串行</b>：读操作也需要获取锁，读多写少场景下性能严重下降。</li>
 *       <li><b>复合操作非原子</b>：迭代、put-if-absent、先检查后执行等组合操作仍需外部同步，
 *           否则可能抛出 {@code ConcurrentModificationException}。</li>
 *       <li><b>迭代器快照失败</b>：迭代器是"快速失败"（fail-fast）风格的——迭代期间若其他线程修改了集合，
 *           立即抛出 {@code ConcurrentModificationException}，而非继续遍历脏数据。</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h3>二、并发容器（Concurrent Containers）</h3>
 * <p>对应目录：{@link com.interview.container.ConcurrentContainers}</p>
 * <ul>
 *   <li><b>核心机制</b>：细粒度锁 + CAS（Compare-And-Swap）无锁算法 + 分段/桶锁。</li>
 *   <li><b>常见类型</b>：
 *     <ul>
 *       <li><b>{@code ConcurrentHashMap}</b>（对应 {@code ConcurrentHashMapDemo}）：
 *         JDK 7 采用分段锁（Segment 数组，每段一把锁）；JDK 8+ 改用 CAS + {@code synchronized} 锁链表/红黑树头节点，
 *         <b>读操作几乎完全无锁</b>，写操作只锁对应的桶。</li>
 *       <li><b>{@code CopyOnWriteArrayList} / {@code CopyOnWriteArraySet}</b>（对应 {@code CopyOnWriteDemo}）：
 *         修改时用 {@code ReentrantLock} 保证单线程复制整个底层数组；<b>读完全无锁，永不阻塞</b>。
 *         适合读多写少的场景。</li>
 *       <li><b>{@code ConcurrentLinkedQueue}</b>：基于 CAS 的无锁非阻塞队列，无锁化高并发。</li>
 *       <li><b>{@code BlockingQueue} 系列</b>（如 {@code ArrayBlockingQueue}、{@code LinkedBlockingQueue}）：
 *         结合 {@code ReentrantLock} + {@code Condition} 实现阻塞的生产者-消费者模式。</li>
 *     </ul>
 *   </li>
 *   <li><b>具体表现</b>：
 *     <ol>
 *       <li><b>读写并发度高</b>：读多写少和并发写入场景下远优于同步容器。</li>
 *       <li><b>弱一致性迭代器</b>：迭代器是"安全失败"（fail-safe）的——遍历过程中其他线程的修改不会被看到，但也不会抛异常。
 *          准确地说，{@code ConcurrentHashMap} 的迭代器反映迭代开始时的状态或其后的部分状态；
 *          {@code CopyOnWriteArrayList} 的迭代器则是创建时刻的数组快照。</li>
 *       <li><b>size() / isEmpty() 等方法的语义减弱</b>：这些方法的返回值在并发环境下是近似值，不保证实时精确。</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h3>三、对比总结</h3>
 * <table border="1" summary="同步容器与并发容器的核心差异对比">
 *   <tr><th>维度</th><th>同步容器</th><th>并发容器</th></tr>
 *   <tr><td>锁粒度</td><td>全局锁（整个对象）</td><td>分段/桶锁 或 CAS 无锁</td></tr>
 *   <tr><td>读并发</td><td>串行（也需要锁）</td><td>几乎无锁，极高并发</td></tr>
 *   <tr><td>写并发</td><td>串行</td><td>只锁对应分片</td></tr>
 *   <tr><td>迭代器风格</td><td>fail-fast（快速失败）</td><td>fail-safe / 弱一致性（安全失败）</td></tr>
 *   <tr><td>复合操作</td><td>需外部手动加锁</td><td>提供原子复合方法（如 {@code putIfAbsent}）</td></tr>
 *   <tr><td>适用场景</td><td>低频访问或完全串行无妨</td><td>高并发读写</td></tr>
 * </table>
 */
package com.interview.container;