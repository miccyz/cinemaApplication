package com.example.cinemaapplication.model;

public enum MovieType {

    ALL("Wszystko"),
    COMEDY("Komedia"),
    DRAMA("Dramat"),
    HORROR("Horror"),
    WAR("Wojna"),
    ACTION("Akcja"),
    ANIME("Anime");

    private String polishName;

    MovieType(String polishName) {
        this.polishName = polishName;
    }

    public String getPolishName() {
        return polishName;
    }
}
