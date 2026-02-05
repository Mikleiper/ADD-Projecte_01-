package com.ra12.projecte1.model;

import java.sql.Timestamp;

public class Exercici {
    private long id;
    private int nivell;
    private String tipus;
    private int durada ;
    private String material;
    private String imagen;
    private Timestamp ultimAcces;
    private Timestamp dataCreated;
    private Timestamp dataUpdated;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public Exercici(int nivell, String tipus, int durada, String material, String imagen, Timestamp ultimAcces,
            Timestamp dataCreated, Timestamp dataUpdated) {
        this.nivell = nivell;
        this.tipus = tipus;
        this.durada = durada;
        this.material = material;
        this.imagen = imagen;
        this.ultimAcces = ultimAcces;
        this.dataCreated = dataCreated;
        this.dataUpdated = dataUpdated;
    }
    
    @Override
    public String toString() {
        return "Exercicis [nivell=" + nivell + ", tipus=" + tipus + ", durada=" + durada + ", material=" + material
                + ", imagen=" + imagen + ", ultimAcces=" + ultimAcces + ", dataCreated=" + dataCreated
                + ", dataUpdated=" + dataUpdated + "]";
    }
    public Exercici() {
    }
 
  
}
