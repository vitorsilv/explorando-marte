package com.example.explorando_marte.models;

import javax.persistence.*;

@Entity
public class Probe extends Control {
    @Id
    @GeneratedValue
    @Column(name = "idProbe")
    private Integer id;
    @Column(name = "positionX", length = 2)
    private int positionX;
    @Column(name = "positionY", length = 2)
    private int positionY;
    @Column(name = "probeDirection", length = 1)
    private String probeDirection;
    @ManyToOne
    @JoinColumn(name = "idPlanet")
    private Planet planet;

    public Probe(){}

    public Probe(int positionX, int positionY, String probeDirection) {
        super(probeDirection);
        this.positionX = positionX;
        this.positionY = positionY;
        this.probeDirection = probeDirection;
    }

    public void move(){
        if(cardinalPoints[0].equals("N")){
            positionY++;
        }else if(cardinalPoints[0].equals("S")){
            positionY--;
        }else if(cardinalPoints[0].equals("E")){
            positionX++;
        }else{
            positionX--;
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getProbeDirection() {
        return probeDirection;
    }

    public void setProbeDirection(String probeDirection) {
        this.probeDirection = probeDirection;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
}
