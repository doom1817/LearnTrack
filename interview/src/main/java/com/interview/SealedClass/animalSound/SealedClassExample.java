package com.interview.SealedClass.animalSound;

public class SealedClassExample {
    public static void main(String[] args) {
        AnimalSound dog = new DogBark();
        AnimalSound catMeow = new CatMeow();
        AnimalSound catPurr = new CatPurr();
        AnimalSound duck = new DuckQuack();

        dog.makeSound();
        catMeow.makeSound();
        catPurr.makeSound();
        duck.makeSound();

        AnimalSound wildDuck = new DuckQuack() {
            @Override
            public void makeSound() {
                System.out.println("Wild Duck says: " + getSound());
            }
        };
        wildDuck.makeSound();
    }
}
