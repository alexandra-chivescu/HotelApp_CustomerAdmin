package com.proiectBD.hotel.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
public class Salarii_departamente {
    @Id
    private String nume_departament;
    private float maxsal;
    private float minsal;
    private float medie;

    public String getNume_departament() {
        return nume_departament;
    }

    public void setNume_departament(String nume_departament) {
        this.nume_departament = nume_departament;
    }

    public float getMaxsal() {
        return maxsal;
    }

    public void setMaxsal(float maxsal) {
        this.maxsal = maxsal;
    }

    public float getMinsal() {
        return minsal;
    }

    public void setMinsal(float minsal) {
        this.minsal = minsal;
    }

    public float getMedie() {
        return medie;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }
}
