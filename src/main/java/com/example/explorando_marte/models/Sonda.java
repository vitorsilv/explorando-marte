package com.example.explorando_marte.models;

public class Sonda extends Controles{
    private String nome;

    private int posicaoX;

    private int posicaoY;

    private String direcaoSonda;

    public Sonda(String nome, int posicaoX, int posicaoY, String direcaoSonda) {
        super(direcaoSonda);
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcaoSonda = direcaoSonda;
    }

    public void mover(){
        if(pontosCardeais[0].equals("N")){
            posicaoY++;
        }else if(pontosCardeais[0].equals("S")){
            posicaoY--;
        }else if(pontosCardeais[0].equals("E")){
            posicaoX++;
        }else{
            posicaoX--;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public String getDirecaoSonda() {
        return direcaoSonda;
    }

    public void setDirecaoSonda(String direcaoSonda) {
        this.direcaoSonda = direcaoSonda;
    }
}
