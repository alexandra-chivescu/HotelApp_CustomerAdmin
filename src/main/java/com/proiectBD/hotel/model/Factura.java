package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name="factura")
public class Factura {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int numar;

    private int id_rezervare;
    private int id_angajat;

    private String data_plata;
    private float valoare_plata;

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public int getId_rezervare() {
        return id_rezervare;
    }

    public void setId_rezervare(int id_rezervare) {
        this.id_rezervare = id_rezervare;
    }

    public int getId_angajat() {
        return id_angajat;
    }

    public void setId_angajat(int id_angajat) {
        this.id_angajat = id_angajat;
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
