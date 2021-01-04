package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "cazare")
public class Cazare {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id_cazare;
    private Integer id_rezervare;
    private Integer id_camera;

    public Integer getId_cazare() {
        return id_cazare;
    }

    public void setId_cazare(Integer id_cazare) {
        this.id_cazare = id_cazare;
    }

    public Integer getId_rezervare() {
        return id_rezervare;
    }

    public void setId_rezervare(Integer id_rezervare) {
        this.id_rezervare = id_rezervare;
    }

    public Integer getId_camera() {
        return id_camera;
    }

    public void setId_camera(Integer id_camera) {
        this.id_camera = id_camera;
    }
}
