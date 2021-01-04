package com.proiectBD.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "camera")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nr_camera;

    private int id_tip;

    public int getId_camera() {
        return nr_camera;
    }

    public void setId_camera(int id_camera) {
        this.nr_camera = id_camera;
    }

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
    }
}