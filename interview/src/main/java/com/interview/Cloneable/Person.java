package com.interview.Cloneable;

/**
 * @author: doom
 * @date: 2026/03/03/09:14
 * @description:
 */
public class Person implements Cloneable{
    private Address address;

    @Override
    public Person clone() {
        try {
            Person person = (Person) super.clone();
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Person(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
