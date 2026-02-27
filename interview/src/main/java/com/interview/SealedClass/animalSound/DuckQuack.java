package com.interview.SealedClass.animalSound;

public non-sealed class DuckQuack extends AnimalSound {
    public DuckQuack() {
        super("Quack!");
    }

    @Override
    public void makeSound() {
        System.out.println("Duck says: " + getSound());
    }
}
