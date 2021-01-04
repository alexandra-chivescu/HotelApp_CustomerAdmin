package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "rezervare")
public class Rezervare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rezervare;

    private int id_client;

    private String zi_sosire;
    private String zi_plecare;
    private float plata_avans;
    private int nr_adulti;
    private int nr_copii;
    private int pat_suplimentar;

    public int getId_rezervare() {
        return id_rezervare;
    }

    public void setId_rezervare(int id_rezervare) {
        this.id_rezervare = id_rezervare;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getZi_sosire() {
        return zi_sosire;
    }

    public void setZi_sosire(String zi_sosire) {
        this.zi_sosire = zi_sosire;
    }

    public String getZi_plecare() {
        return zi_plecare;
    }

    public void setZi_plecare(String zi_plecare) {
        this.zi_plecare = zi_plecare;
    }

    public float getPlata_avans() {
        return plata_avans;
    }

    public void setPlata_avans(float plata_avans) {
        this.plata_avans = plata_avans;
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
