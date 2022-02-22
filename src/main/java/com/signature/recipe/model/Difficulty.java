package com.signature.recipe.model;

public enum Difficulty {

    EASY("Easy"),
    MODERATE("Moderate"),
    HARD("Hard");

    Difficulty(String name) {
        this.name = name;
    }

    private final String name;

    public String getDisplayName() {
        return this.name;
    }
}