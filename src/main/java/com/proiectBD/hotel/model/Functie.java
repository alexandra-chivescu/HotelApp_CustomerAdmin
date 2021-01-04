package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name="functie")
public class Functie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_functie;

    private String nume_functie;

    public int getId_functie() {
        return id_functie;
    }

    public void setId_functie(int id_functie) {
        this.id_functie = id_functie;
    }

    public String getNume_functie() {
        return nume_functie;
    }

    public void setNume_functie(String nume_functie) {
        this.nume_functie = nume_functie;
    }
}
