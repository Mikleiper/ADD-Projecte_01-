package com.ra12.projecte1.dto;

import java.sql.Timestamp;

import com.ra12.projecte1.model.Exercici;

public class ExerciciResponseDTO {
    private long id;
    private int nivell;
    private String tipus;
    private int durada ;
    private String material;
    private String imagen;
    private Timestamp ultimAcces;
    private Timestamp dataCreated;
    private Timestamp dataUpdated;
    

    public ExerciciResponseDTO() {
    }

    public ExerciciResponseDTO(long id, int nivell, String tipus, int durada, String material, String imagen,
            Timestamp ultimAcces, Timestamp dataCreated, Timestamp dataUpdated) {
        this.id = id;
        this.nivell = nivell;
        this.tipus = tipus;
        this.durada = durada;
        this.material = material;
        this.imagen = imagen;
        this.ultimAcces = ultimAcces;
        this.dataCreated = dataCreated;
        this.dataUpdated = dataUpdated;
    }

    // Constructor que accepta l'entitat Exercici complerta per facilitar la conversió
    public ExerciciResponseDTO(Exercici exercici) {
        this.id = exercici.getId();
        this.nivell = exercici.getNivell();
        this.tipus = exercici.getTipus();
        this.durada = exercici.getDurada();
        this.material = exercici.getMaterial();
        this.imagen = exercici.getImagen(); 
        this.ultimAcces = exercici.getUltimAcces();
        this.dataCreated = exercici.getDataCreated();
        this.dataUpdated = exercici.getDataUpdated();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Timestamp getUltimAcces() {
        return ultimAcces;
    }

    public void setUltimAcces(Timestamp ultimAcces) {
        this.ultimAcces = ultimAcces;
    }

    public Timestamp getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(Timestamp dataCreated) {
        this.dataCreated = dataCreated;
    }

    public Timestamp getDataUpdated() {
        return dataUpdated;
    }

    public void setDataUpdated(Timestamp dataUpdated) {
        this.dataUpdated = dataUpdated;
    }

    @Override
    public String toString() {
        return "Exercici [id=" + id + ", nivell=" + nivell + ", tipus=" + tipus + ", durada=" + durada
                + ", material=" + material + ", UrlImatge=" + imagen + ", últim accés=" + ultimAcces + ", data de creació="
                + dataCreated + ", data d'actualització=" + dataUpdated + "]";
    }
}
