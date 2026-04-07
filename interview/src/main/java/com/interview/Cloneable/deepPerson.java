package com.interview.Cloneable;

/**
 * @author: doom
 * @date: 2026/03/03/09:19
 * @description:
 */
public class deepPerson implements Cloneable{
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public deepPerson clone() {
        try {
            deepPerson person = (deepPerson) super.clone();
            person.setAddress(person.getAddress().clone());
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public deepPerson(Address address) {
        this.address = address;
    }
}
