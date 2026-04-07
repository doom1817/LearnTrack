package com.interview.Cloneable;

/**
 * @author: doom
 * @date: 2026/03/03/09:15
 * @description:
 */
public class init {
    public static void main(String[] args) {
        //浅拷贝
        Person person1 = new Person(new Address("武汉"));
        Person personCopy = person1.clone();
        System.out.println(person1.getAddress() == personCopy.getAddress());
        //引用拷贝
        Person original = new Person(new Address("武汉"));
        Person refCopy = original; //直接赋值
        System.out.println(original == refCopy);
        //深拷贝
        deepPerson person2 = new deepPerson(new Address("武汉"));
        deepPerson personCopy2 = person2.clone();
        System.out.println(person2.getAddress() == personCopy2.getAddress());
    }
}
