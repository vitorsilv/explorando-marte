package com.example.explorando_marte.models;

public abstract class Controles {
    String[] pontosCardeais = {"N", "E", "S", "W"};

    public Controles(String direcao){
        while(!pontosCardeais.equals(direcao.toUpperCase())){
            virarDireita();
        }
    }
    //Fila Infinita
    public void virarDireita(){
        String auxiliar = pontosCardeais[0];
        for (int i = 1; i < pontosCardeais.length; i++) {
            pontosCardeais[i-1] = pontosCardeais[i];
        }
        pontosCardeais[3] = auxiliar;
    }
    //Pilha Infinita
    public void virarEsquerda(){
        String auxiliar = pontosCardeais[3];
        for (int i = pontosCardeais.length-1;i > 0; i++) {
            pontosCardeais[i] = pontosCardeais[i-1];
        }
        pontosCardeais[0] = auxiliar;
    }

    public abstract void mover();
}
