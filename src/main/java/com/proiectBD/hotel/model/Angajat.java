package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name="angajat")
public class Angajat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_angajat;

    private int id_functie;
    private int id_departament;

    private String nume;
    private String prenume;
    private String email;
    private String cnp;
    private String telefon;
    private String data_angajare;
    private float salariu;
    private int puncte_loialitate;

    public int getId_angajat() {
        return id_angajat;
    }

    public void setId_angajat(int id_angajat) {
        this.id_angajat = id_angajat;
    }

    public int getId_functie() {
        return id_functie;
    }

    public void setId_functie(int id_functie) {
        this.id_functie = id_functie;
    }

    public int getId_departament() {
        return id_departament;
    }

    public void setId_departament(int id_departament) {
        this.id_departament = id_departament;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getData_angajare() {
        return data_angajare;
    }

    public void setData_angajare(String data_angajare) {
        this.data_angajare = data_angajare;
    }

    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }

    public int getPuncte_loialitate() {
        return puncte_loialitate;
    }

    public void setPuncte_loialitate(int puncte_loialitate) {
        this.puncte_loialitate = puncte_loialitate;
    }
}
