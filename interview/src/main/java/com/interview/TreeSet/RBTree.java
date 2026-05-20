package com.interview.TreeSet;

/**
 * 红黑树实现
 * <p>
 * 红黑树是一种自平衡二叉搜索树，通过颜色标记和旋转操作保持树的平衡性
 * 保证查找、插入、删除操作的时间复杂度为 O(log n)
 * <p>
 * 红黑树五大性质：
 * 1. 每个节点要么是红色，要么是黑色
 * 2. 根节点是黑色
 * 3. 所有叶子节点（NIL）都是黑色
 * 4. 如果一个节点是红色，则它的两个子节点都是黑色（不能有连续的两个红色节点）
 * 5. 从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点
 *
 * @param <K> 键类型，必须实现 Comparable 接口
 * @param <V> 值类型
 * @author: doom
 * @date: 2026/05/20/11:34
 * @description:
 */
public class RBTree<K extends Comparable<K>, V> {

    // 节点颜色常量
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    /**
     * 红黑树节点内部类
     *
     * @param <K> 键类型
     * @param <V> 值类型
     */
    private static class RBNode<K, V> {
        K key;
        V value;
        RBNode<K, V> left;
        RBNode<K, V> right;
        RBNode<K, V> parent;
        boolean color;

        RBNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;   // 新节点默认为红色
        }
    }

    private RBNode<K, V> root;
    private final RBNode<K, V> NIL;  // 哨兵节点，代表所有叶子

    public RBTree() {
        NIL = new RBNode<>(null, null);
        NIL.color = BLACK;
        NIL.left = null;
        NIL.right = null;
        root = NIL;
    }

    /**
     * 判断节点是否为 NIL（哨兵节点）
     *
     * @param node 待判断的节点
     * @return true 表示是 NIL 节点或 null，false 表示有效节点
     */
    private boolean isNil(RBNode<K, V> node) {
        return node == NIL || node == null;
    }

    /**
     * 左旋操作
     * <p>
     * 将节点 x 和其右子节点 y 进行左旋，y 成为新的子树根节点
     * 用于修复红黑树性质时的结构调整
     * <pre>
     *     x                    y
     *    / \                  / \
     *   a   y      =>       x   c
     *      / \             / \
     *     b   c           a   b
     * </pre>
     *
     * @param x 需要左旋的节点
     */
    private void leftRotate(RBNode<K, V> x) {
        RBNode<K, V> y = x.right;
        x.right = y.left;
        if (!isNil(y.left)) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (isNil(x.parent)) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋操作
     * <p>
     * 将节点 y 和其左子节点 x 进行右旋，x 成为新的子树根节点
     * 用于修复红黑树性质时的结构调整
     * <pre>
     *       y                x
     *      / \              / \
     *     x   c    =>     a   y
     *    / \                  / \
     *   a   b                b   c
     * </pre>
     *
     * @param y 需要右旋的节点
     */
    private void rightRotate(RBNode<K, V> y) {
        RBNode<K, V> x = y.left;
        y.left = x.right;
        if (!isNil(x.right)) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (isNil(y.parent)) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    /**
     * 插入键值对
     * <p>
     * 先按照二叉搜索树的规则插入新节点（默认为红色），然后通过 insertFixup 修复红黑树性质
     *
     * @param key   键，不能为 null
     * @param value 值
     */
    public void put(K key, V value) {
        RBNode<K, V> z = new RBNode<>(key, value);
        z.left = NIL;
        z.right = NIL;

        RBNode<K, V> y = NIL;
        RBNode<K, V> x = root;

        // 二叉搜索树插入
        while (!isNil(x)) {
            y = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                // 键已存在，更新值
                x.value = value;
                return;
            }
        }

        z.parent = y;
        if (isNil(y)) {
            root = z;
        } else if (key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }

        // 修复红黑性质
        insertFixup(z);
    }

    /**
     * 插入后修复红黑树性质
     * <p>
     * 通过变色和旋转操作恢复红黑树的五大性质
     * 主要处理三种情况：
     * Case 1: 叔父节点为红色 - 父节点和叔父节点变黑，祖父节点变红，继续向上检查
     * Case 2: 叔父节点为黑色且当前节点是右孩子 - 左旋转化为 Case 3
     * Case 3: 叔父节点为黑色且当前节点是左孩子 - 父节点变黑，祖父节点变红，右旋
     *
     * @param z 新插入的节点
     */
    private void insertFixup(RBNode<K, V> z) {
        while (z.parent != null && z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) { // 父节点是祖父的左子
                RBNode<K, V> y = z.parent.parent.right; // 叔父节点
                if (y.color == RED) {   // Case 1: 叔父为红
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {                // 叔父为黑
                    if (z == z.parent.right) { // Case 2: z是右孩子 -> 左旋
                        z = z.parent;
                        leftRotate(z);
                    }
                    // Case 3: z是左孩子 -> 右旋并变色
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                }
            } else { // 对称情况：父节点是祖父的右子
                RBNode<K, V> y = z.parent.parent.left;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    /**
     * 删除指定键的节点
     * <p>
     * 先找到要删除的节点，然后根据节点的子节点情况进行删除：
     * 1. 无左子节点：用右子节点替换
     * 2. 无右子节点：用左子节点替换
     * 3. 有两个子节点：找到右子树的最小节点作为替代者
     * 如果删除的是黑色节点，需要通过 deleteFixup 修复红黑树性质
     *
     * @param key 要删除的键
     */
    public void remove(K key) {
        RBNode<K, V> z = searchNode(key);
        if (isNil(z)) return;

        RBNode<K, V> y = z;
        RBNode<K, V> x;
        boolean yOriginalColor = y.color;

        if (isNil(z.left)) {
            x = z.right;
            transplant(z, z.right);
        } else if (isNil(z.right)) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == BLACK) {
            deleteFixup(x);
        }
    }

    /**
     * 用子树 v 替换子树 u
     * <p>
     * 将节点 u 从其父节点中移除，并用节点 v 替代其位置
     * 这是删除操作中的基础替换操作
     *
     * @param u 被替换的节点
     * @param v 替换节点
     */
    private void transplant(RBNode<K, V> u, RBNode<K, V> v) {
        if (isNil(u.parent)) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    /**
     * 删除后修复红黑树性质
     * <p>
     * 当删除黑色节点后，可能导致某些路径的黑色节点数量不一致
     * 通过变色和旋转操作恢复红黑树性质
     * 主要处理四种情况：
     * Case 1: 兄弟节点为红色 - 兄弟变黑，父节点变红，左旋，转化为其他情况
     * Case 2: 兄弟节点的两个子节点都是黑色 - 兄弟变红，向上检查父节点
     * Case 3: 兄弟节点的左子为红，右子为黑 - 左子变黑，兄弟变红，右旋，转化为 Case 4
     * Case 4: 兄弟节点的右子为红 - 兄弟继承父节点颜色，父节点变黑，右子变黑，左旋
     *
     * @param x 删除后需要修复的节点（可能是实际删除节点的子节点）
     */
    private void deleteFixup(RBNode<K, V> x) {
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                RBNode<K, V> w = x.parent.right; // 兄弟节点
                // Case 1: 兄弟为红 -> 变色并左旋
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                // Case 2: 兄弟的两个孩子都是黑
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    // Case 3: 兄弟的右孩子是黑，左孩子是红 -> 右旋并变色
                    if (w.right.color == BLACK) {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // Case 4: 兄弟的右孩子是红 -> 左旋并变色
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else { // 对称情况
                RBNode<K, V> w = x.parent.left;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    /**
     * 根据键查找节点
     *
     * @param key 要查找的键
     * @return 找到的节点，未找到返回 NIL
     */
    private RBNode<K, V> searchNode(K key) {
        RBNode<K, V> cur = root;
        while (!isNil(cur)) {
            int cmp = key.compareTo(cur.key);
            if (cmp < 0) cur = cur.left;
            else if (cmp > 0) cur = cur.right;
            else return cur;
        }
        return NIL;
    }

    /**
     * 查找以 node 为根的子树中的最小值节点
     *
     * @param node 子树根节点
     * @return 最小值节点
     */
    private RBNode<K, V> minimum(RBNode<K, V> node) {
        while (!isNil(node.left)) node = node.left;
        return node;
    }

    /**
     * 中序遍历打印树的所有节点（辅助调试）
     * <p>
     * 按照键的升序输出所有节点及其颜色
     */
    public void inorderPrint() {
        inorderPrint(root);
        System.out.println();
    }

    /**
     * 递归进行中序遍历打印
     *
     * @param node 当前节点
     */
    private void inorderPrint(RBNode<K, V> node) {
        if (isNil(node)) return;
        inorderPrint(node.left);
        String colorStr = node.color == RED ? "R" : "B";
        System.out.print(node.key + "(" + colorStr + ") ");
        inorderPrint(node.right);
    }

    // 测试
    public static void main(String[] args) {
        RBTree<Integer, String> tree = new RBTree<>();
        int[] keys = {10, 5, 15, 3, 7, 13, 18, 1, 6};
        for (int key : keys) {
            tree.put(key, "val" + key);
        }
        System.out.print("初始中序遍历: ");
        tree.inorderPrint();

        System.out.println("删除 5");
        tree.remove(5);
        System.out.print("删除后中序遍历: ");
        tree.inorderPrint();

        System.out.println("删除 10");
        tree.remove(10);
        System.out.print("删除后中序遍历: ");
        tree.inorderPrint();

        System.out.println("插入 20");
        tree.put(20, "val20");
        System.out.print("插入后中序遍历: ");
        tree.inorderPrint();
    }
}
