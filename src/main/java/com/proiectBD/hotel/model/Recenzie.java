package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name="recenzie")
public class Recenzie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_recenzie;
    private int id_client;

    private String subiect;
    private String data;

    public int getId_recenzie() {
        return id_recenzie;
    }

    public void setId_recenzie(int id_recenzie) {
        this.id_recenzie = id_recenzie;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getSubiect() {
        return subiect;
    }

    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
