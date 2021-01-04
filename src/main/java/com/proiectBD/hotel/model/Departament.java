package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name="departament")
public class Departament {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_departament;

    private String nume_departament;

    public int getId_departament() {
        return id_departament;
    }

    public void setId_departament(int id_departament) {
        this.id_departament = id_departament;
    }

    public String getNume_departament() {
        return nume_departament;
    }

    public void setNume_departament(String nume_departament) {
        this.nume_departament = nume_departament;
    }
}
