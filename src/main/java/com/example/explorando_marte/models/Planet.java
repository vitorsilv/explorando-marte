package com.example.explorando_marte.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Planet {
    @Id
    @GeneratedValue
    @Column(name = "idPlanet")
    private Integer id;
    @Column(name = "planetName",length = 30, nullable = false, unique = true)
    private String planetName;
    @Column(length = 2, nullable = false)
    private Integer x;
    @Column(length = 2, nullable = false)
    private Integer y;

    public Planet(){}

    public Planet(String planetName, Integer limitX, Integer limitY) {
        this.planetName = planetName;
        this.x = limitX;
        this.y = limitY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }


}
