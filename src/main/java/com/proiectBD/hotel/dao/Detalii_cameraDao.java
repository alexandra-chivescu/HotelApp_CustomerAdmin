package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Client;
import com.proiectBD.hotel.model.Date_factura;
import com.proiectBD.hotel.model.Detalii_camera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface Detalii_cameraDao extends CrudRepository<Detalii_camera, Integer> {

    List<Detalii_camera> findAll();

    Detalii_camera findById(int id);

}