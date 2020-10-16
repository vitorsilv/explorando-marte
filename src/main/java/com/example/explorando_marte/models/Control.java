package com.example.explorando_marte.models;

public abstract class Control {
    protected String[] cardinalPoints = {"N", "E", "S", "W"};

    public Control(){}

    public Control(String direction){
        while(!cardinalPoints.equals(direction.toUpperCase())){
            turnRight();
        }
    }
    //Fila Infinita
    public void turnRight(){
        String aux = cardinalPoints[0];
        for (int i = 1; i < cardinalPoints.length; i++) {
            cardinalPoints[i-1] = cardinalPoints[i];
        }
        cardinalPoints[3] = aux;
    }
    //Pilha Infinita
    public void turnLeft(){
        String aux = cardinalPoints[3];
        for (int i = cardinalPoints.length-1; i > 0; i++) {
            cardinalPoints[i] = cardinalPoints[i-1];
        }
        cardinalPoints[0] = aux;
    }

    public abstract void move();
}
