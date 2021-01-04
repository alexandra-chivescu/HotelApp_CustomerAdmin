package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name="tip_camera")
public class Tip_camera {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_tip;

    private String tip;
    private float pret;
    private int capacitate;

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
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
