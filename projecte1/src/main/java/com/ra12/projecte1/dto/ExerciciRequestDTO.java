package com.ra12.projecte1.dto;

public class ExerciciRequestDTO {

    private int nivell;
    private String tipus;
    private int durada;
    private String material;

    //construcot buit per a q Spring pugui rebre JSON i convertir-ho a objecte
    public ExerciciRequestDTO() {
    }

    public ExerciciRequestDTO(int nivell, String tipus, int durada, String material) {
        this.nivell = nivell;
        this.tipus = tipus;
        this.durada = durada;
        this.material = material;
    }

    public int getNivell() {
        return nivell;
    }

    public void setNivell(int nivell) {
        this.nivell = nivell;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getDurada() {
        return durada;
    }

    public void setDurada(int durada) {
        this.durada = durada;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    
}