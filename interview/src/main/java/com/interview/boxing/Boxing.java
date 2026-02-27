package com.interview.boxing;

/**
 * @author : doom
 * @date: 2026/02/27/17:18
 * @description:
 *   用于测试装箱和拆箱
 *
 */
public class Boxing {

    //自动装箱
    int primitiveInt = 100;
    Integer wrapperInt = primitiveInt; // 自动装箱
    //实际装箱是 Integer wrapperInt = Integer.valueOf(primitiveInt);
    Integer wrapperInt1 = Integer.valueOf(primitiveInt);

    /**
     *  review
     *   1. 自动装箱和拆箱都是JVM自动进行的，不需要程序员手动进行。
     *   2. 自动装箱和拆箱都是基于对象池的，对象池中已经存在的对象，则直接返回对象池中的对象，否则创建新的对象并放入对象池中。
     *   3. 自动装箱1会出现装箱缓存机制，但是拆箱不会出现缓存机制
     */
    // 装箱缓存机制

//            测试使用 new Integer() 创建对象（即使值在范围内，也不会复用缓存）
//            从 Java 9 开始，Integer 的构造函数被标记为弃用，
//            Integer c1 = new Integer(100);
//            Integer c2 = new Integer(100);
//            System.out.println("new Integer(100)：c1 == c2 ? " + (c1 == c2)); // false，不同对象

    // 测试混合情况：自动装箱的缓存对象 与 new 创建的对象
//            System.out.println("自动装箱的100 与 new Integer(100) 是否相等？ " + (a1 == c1)); // false

    private static void boxingCache(){
            // 测试缓存范围内的值 (-128 ~ 127)
            Integer a1 = 100;
            Integer a2 = 100;
            System.out.println("100 使用自动装箱：a1 == a2 ? " + (a1 == a2)); // true，同一个对象

            // 测试缓存范围外的值 (大于127)
            Integer b1 = 200;
            Integer b2 = 200;
            System.out.println("200 使用自动装箱：b1 == b2 ? " + (b1 == b2)); // false，不同对象

            // 测试使用 valueOf 方法直接调用（与自动装箱等价）
            Integer d1 = Integer.valueOf(100);
            Integer d2 = Integer.valueOf(100);
            System.out.println("Integer.valueOf(100)：d1 == d2 ? " + (d1 == d2)); // true

            // 测试边界值 -128 和 127
            Integer e1 = -128;
            Integer e2 = -128;
            System.out.println("-128：e1 == e2 ? " + (e1 == e2)); // true

            Integer f1 = 127;
            Integer f2 = 127;
            System.out.println("127：f1 == f2 ? " + (f1 == f2)); // true
        }

    public static void main(String[] args) {
       boxingCache();
    }

    //自动拆箱
    Integer objectInt2 = 200;
    int primitiveInt2 = objectInt2;
    //实际拆箱是 int primitiveInt2 = objectInt2.intValue();
    int primitiveInt3 = objectInt2.intValue();

}
