package com.proiectBD.hotel.dao;


import com.proiectBD.hotel.model.Client;
import com.proiectBD.hotel.model.Rezervare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.awt.geom.RectangularShape;
import java.util.List;

@Repository
@Component
public interface RezervareDao extends CrudRepository<Rezervare, Integer> {
    List<Rezervare> findAll();

    @Query(value ="select * from rezervare \n" +
            "join cazare on rezervare.id_rezervare = cazare.id_rezervare \n" +
            "join camera on camera.nr_camera = cazare.id_camera \n" +
            "join tip_camera on camera.id_tip = tip_camera.id_tip \n" +
            "where tip_camera.id_tip = :id_tip_cautat", nativeQuery = true)
    List<Rezervare> findRezervareTipCautat(int id_tip_cautat);

    Rezervare findById(int id);

}
