package com.proiectBD.hotel.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
public class Cazari_astazi {
    @Id
    private int nr_camera;
    private String tip;
    private int nr_adulti;
    private int nr_copii;
    private int pat_suplimentar;

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

    public int getNr_adulti() {
        return nr_adulti;
    }

    public void setNr_adulti(int nr_adulti) {
        this.nr_adulti = nr_adulti;
    }

    public int getNr_copii() {
        return nr_copii;
    }

    public void setNr_copii(int nr_copii) {
        this.nr_copii = nr_copii;
    }

    public int getPat_suplimentar() {
        return pat_suplimentar;
    }

    public void setPat_suplimentar(int pat_suplimentar) {
        this.pat_suplimentar = pat_suplimentar;
    }
}
