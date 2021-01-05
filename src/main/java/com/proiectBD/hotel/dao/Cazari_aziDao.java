package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Cazari_astazi;
import com.proiectBD.hotel.model.Client;
import com.proiectBD.hotel.model.Date_factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface Cazari_aziDao extends CrudRepository<Cazari_astazi, Integer> {

    List<Cazari_astazi> findAll();

    Cazari_astazi findById(int id);

}
