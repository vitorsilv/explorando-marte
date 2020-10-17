package com.example.explorando_marte.models;

public interface Control {
    String[] cardinalPoints = {"N", "E", "S", "W"};

    void turnRight();
    void turnLeft();
    void move();
}
