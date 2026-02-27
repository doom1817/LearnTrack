package com.interview.SealedClass.animalSound;

public final class CatMeow extends CatSound {
    public CatMeow() {
        super("Meow!");
    }

    @Override
    public void makeSound() {
        System.out.println("Cat says: " + getSound());
    }
}
