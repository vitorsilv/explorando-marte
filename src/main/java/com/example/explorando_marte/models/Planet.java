package com.example.explorando_marte.models;

import javax.persistence.*;

@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlanet")
    private int id;
    @Column(name = "planetName",length = 30, nullable = false, unique = true)
    private String planetName;
    @Column(length = 2, nullable = false)
    private int x;
    @Column(length = 2, nullable = false)
    private int y;

    public Planet(){}

    public Planet(String planetName, int limitX, int limitY) {
        this.planetName = planetName;
        this.x = limitX;
        this.y = limitY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
