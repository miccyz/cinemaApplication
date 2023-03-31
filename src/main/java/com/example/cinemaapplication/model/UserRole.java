package com.example.cinemaapplication.model;

public enum UserRole {

    ADMIN("Admin"),
    USER("Użytkownik");

    private String polishName;

    UserRole(String polishName) {
        this.polishName = polishName;
    }

    public String getPolishName() {
        return polishName;
    }
}
