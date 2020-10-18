package com.example.explorando_marte.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Planet {

    @ApiModelProperty(value = "Código de identificação do planeta")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlanet")
    private int id;

    @ApiModelProperty(value = "Nome do planeta")
    @Column(name = "planetName",length = 30, nullable = false, unique = true)
    private String planetName;

    @ApiModelProperty(value = "Posição X limite do planeta")
    @Column(length = 2, nullable = false)
    private int x;

    @ApiModelProperty(value = "Posição Y limite do planeta")
    @Column(length = 2, nullable = false)
    private int y;

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
