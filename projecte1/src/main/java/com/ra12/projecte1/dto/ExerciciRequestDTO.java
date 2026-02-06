package com.ra12.projecte1.dto;

import java.sql.Timestamp;

import com.ra12.projecte1.model.Exercici;

public class ExerciciRequestDTO {

    private int nivell;
    private String tipus;
    private int durada;
    private String material;
    private Timestamp ultimAcces;

    //construcot buit per a q Spring pugui rebre JSON i convertir-ho a objecte
    public ExerciciRequestDTO() {
    }

    public ExerciciRequestDTO(int nivell, String tipus, int durada, String material, Timestamp ultimAcces) {
        this.nivell = nivell;
        this.tipus = tipus;
        this.durada = durada;
        this.material = material;
        this.ultimAcces = ultimAcces;
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
    
    public Exercici toExercici(){
        return new Exercici(nivell, tipus, durada, material, ultimAcces, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    }
}