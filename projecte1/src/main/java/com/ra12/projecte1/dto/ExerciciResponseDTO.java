package com.ra12.projecte1.dto;

import java.sql.Timestamp;

public class ExerciciResponseDTO {
    private long id;
    private int nivell;
    private String tipus;
    private int durada ;
    private String material;
    private String imagen;
    private Timestamp ultimAcces;

    @Override
    public String toString() {
        return "Exercici [id=" + id + ", nivell=" + nivell + ", tipus=" + tipus + ", durada=" + durada
                + ", material=" + material + ", UrlImatge=" + imagen + ", ultimAcces=" + ultimAcces + "]";
    }
}
