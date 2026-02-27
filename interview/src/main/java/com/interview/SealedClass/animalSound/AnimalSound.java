package com.interview.SealedClass.animalSound;

public abstract sealed class AnimalSound permits DogBark, CatSound, DuckQuack {

    private final String sound;

    protected AnimalSound(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    public abstract void makeSound();
}
