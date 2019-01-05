package com.petrego.domain;

public enum PetFood {
    dog("bone"),
    cat("fish"),
    chicken("corn"),
    snake("mouse");

    private String food;

    PetFood(final String food) {
        this.food = food;
    }

    public String getFood() {
        return food;
    }
}
