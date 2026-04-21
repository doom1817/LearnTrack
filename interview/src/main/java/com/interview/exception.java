package com.interview;

/**
 * @author: doom
 * @date: 2026/04/26/16:36
 * @description:
 *  自定义异常
 */
public class exception {
    public static void main(String[] args) {
        try {
            // 主动抛出一个自定义的异常
            throw new MyLocalizedException("Detailed Error Message");
        } catch (MyLocalizedException e) {
            // 2. 演示各个方法的输出效果
            System.out.println("---------- 方法输出对比 ----------");

            // getMessage(): 返回异常发生时的详细消息字符串
            System.out.println("getMessage(): " + e.getMessage());

            // toString(): 返回异常的简要描述（格式通常为：异常全类名: 详细消息）
            System.out.println("toString(): " + e.toString());

            // getLocalizedMessage(): 返回本地化信息。
            // 如果子类没有覆盖该方法，则返回与 getMessage() 相同的结果
            System.out.println("getLocalizedMessage(): " + e.getLocalizedMessage());

            System.out.println("---------- printStackTrace() 输出 ----------");
            // printStackTrace(): 在控制台打印异常的堆栈跟踪信息（包含异常类型、消息及发生位置）
            e.printStackTrace();
        }
    }
}
class MyLocalizedException extends Exception{
    public MyLocalizedException(String message){
        super(message);
    }
    @Override
    public String getLocalizedMessage(){
        return "使用自定义的本地化异常";
    }
}
