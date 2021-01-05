package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Client;
import com.proiectBD.hotel.model.Date_factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface Date_facturaDao extends CrudRepository<Date_factura, Integer> {

    List<Date_factura> findAll();

    Date_factura findById(int id);

}
