package com.interview.SealedClass.animalSound;

public final class CatPurr extends CatSound {
    public CatPurr() {
        super("Purr...");
    }

    @Override
    public void makeSound() {
        System.out.println("Cat says: " + getSound());
    }
}
