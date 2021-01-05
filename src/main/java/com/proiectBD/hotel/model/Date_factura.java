package com.proiectBD.hotel.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
public class Date_factura {
    @Id
    private int numar;
    private String nume;
    private String prenume;
    private String data_plata;
    private float valoare_plata;

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getData_plata() {
        return data_plata;
    }

    public void setData_plata(String data_plata) {
        this.data_plata = data_plata;
    }

    public float getValoare_plata() {
        return valoare_plata;
    }

    public void setValoare_plata(float valoare_plata) {
        this.valoare_plata = valoare_plata;
    }
}
