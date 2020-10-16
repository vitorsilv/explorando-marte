package com.example.explorando_marte.models;

public class Mapa extends Sonda{
    private Integer x;

    private Integer y;

    public Mapa(Integer limiteX, Integer limiteY,
                String nomeSonda, int posicaoXSonda, int posicaoYSonda, String direcaoSonda) {
        super(nomeSonda, posicaoXSonda, posicaoYSonda, direcaoSonda);
        this.x = limiteX;
        this.y = limiteY;
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
