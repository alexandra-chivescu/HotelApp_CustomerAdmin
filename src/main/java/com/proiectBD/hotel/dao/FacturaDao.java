package com.proiectBD.hotel.dao;


import com.proiectBD.hotel.model.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface FacturaDao extends CrudRepository<Factura, Integer> {
    List<Factura> findAll();
}