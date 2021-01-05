package com.proiectBD.hotel.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Immutable
public class Detalii_camera {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int nr_camera;

    private String tip;
    private float pret;
    private int capacitate;

    public int getNr_camera() {
        return nr_camera;
    }

    public void setNr_camera(int nr_camera) {
        this.nr_camera = nr_camera;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }
}
