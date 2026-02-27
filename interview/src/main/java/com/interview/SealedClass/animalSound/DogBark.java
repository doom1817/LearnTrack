package com.interview.SealedClass.animalSound;

/**
 * @author: doom
 * @date: 2026/02/26/17:45
 * @description:
 */
final class DogBark extends AnimalSound {
    public DogBark() {
        super("Woof!");
    }

    @Override
    public void makeSound() {
        System.out.println("Dog says: " + getSound());
    }
}
