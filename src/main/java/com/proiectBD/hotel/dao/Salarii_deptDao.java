package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Client;
import com.proiectBD.hotel.model.Date_factura;
import com.proiectBD.hotel.model.Salarii_departamente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface Salarii_deptDao extends CrudRepository<Salarii_departamente, Integer> {

    List<Salarii_departamente> findAll();

    Salarii_departamente findById(int id);

}
