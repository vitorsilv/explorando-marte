package com.example.explorando_marte.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.*;

@Entity
public class Probe implements Control {

    @ApiModelProperty(value = "Código de identificação da sonda")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProbe")
    private int id;

    @ApiModelProperty(value = "Posição X da sonda")
    @Column(name = "positionX", length = 2)
    private int positionX;

    @ApiModelProperty(value = "Posição Y da sonda")
    @Column(name = "positionY", length = 2)
    private int positionY;

    @ApiModelProperty(value = "Direção em que a sonda está virada")
    @Column(name = "probeDirection", length = 1)
    private String probeDirection;

    @ApiModelProperty(value = "Planeta o qual a sonda pertence")
    @ManyToOne
    @JoinColumn(name = "idPlanet")
    private Planet planet;

    @Transient
    private ArrayList<String> cardinalPoints = new ArrayList<String>(Arrays.asList("N", "E", "S", "W"));

    public void turnRight(){
        int actualPosition =  cardinalPoints.indexOf(probeDirection);
        if(actualPosition==3){
            probeDirection = cardinalPoints.get(0);
        }else{
            probeDirection = cardinalPoints.get(actualPosition+1);
        }
    }

    public void turnLeft(){
        int actualPosition =  cardinalPoints.indexOf(probeDirection);
        if(actualPosition==0){
            probeDirection = cardinalPoints.get(3);
        }else{
            probeDirection = cardinalPoints.get(actualPosition-1);
        }
    }

    public void move(){
        if(this.probeDirection.equals("N") && this.positionY < this.planet.getY()){
            this.positionY++;
        }else if(this.probeDirection.equals("S") && this.positionY > 0){
            this.positionY--;
        }else if(this.probeDirection.equals("E") && this.positionX < this.planet.getX()){
            this.positionX++;
        }else if(this.probeDirection.equals("W") && this.positionX > 0){
            this.positionX--;
        }else{
            System.out.println("Movimento anulado");
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
