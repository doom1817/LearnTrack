package com.interview.Object;

/**
 * @author: doom
 * @date: 2026/03/19/11:50
 * @description:
 */
public class finalizeTest {
    public static void main(String[] args) {
        finalizeTest test = new finalizeTest();
        test = null;
        System.gc();
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize方法被调用");
    }
}
