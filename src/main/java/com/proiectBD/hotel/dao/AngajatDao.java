package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Angajat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface AngajatDao extends CrudRepository<Angajat, Integer> {
    List<Angajat> findAll();
}