package com.ra12.projecte1.dto;

import java.sql.Timestamp;

import com.ra12.projecte1.model.Exercici;

public class ExerciciRequestDTO {
    private int nivell;
    private String tipus;
    private int durada ;
    private String material;
    private String imagen;
    private Timestamp ultimAcces;

    public Exercici toExercici(){
        return new Exercici(nivell, tipus, durada, material, imagen, ultimAcces, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    }
}