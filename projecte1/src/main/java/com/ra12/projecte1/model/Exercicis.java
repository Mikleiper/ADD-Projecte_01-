package com.ra12.projecte1.model;

public class Exercicis {
    private int nivell;
    private String tipus;
    private int durada ;
    private String material;
    private String imagen;
    
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
    public Exercicis(int nivell, String tipus, int durada, String material, String imagen) {
        this.nivell = nivell;
        this.tipus = tipus;
        this.durada = durada;
        this.material = material;
        this.imagen = imagen;
    }
    @Override
    public String toString() {
        return "Exercicis [nivell=" + nivell + ", tipus=" + tipus + ", durada=" + durada + ", material=" + material
                + ", imagen=" + imagen + "]";
    }

    

    


}
