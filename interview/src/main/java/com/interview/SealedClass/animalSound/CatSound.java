package com.interview.SealedClass.animalSound;

public abstract sealed class CatSound extends AnimalSound permits CatMeow, CatPurr {

    protected CatSound(String sound) {
        super(sound);
    }
}
