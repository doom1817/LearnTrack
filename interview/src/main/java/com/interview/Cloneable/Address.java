package com.interview.Cloneable;

/**
 * @author: doom
 * @date: 2026/03/03/09:12
 * @description:
 */
public class Address implements Cloneable{
    private String name;
    @Override
    public Address clone(){
        try{
            return (Address)super.clone();
        }catch (CloneNotSupportedException e){
            throw  new AssertionError();
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address(String name) {
        this.name = name;
    }

}
