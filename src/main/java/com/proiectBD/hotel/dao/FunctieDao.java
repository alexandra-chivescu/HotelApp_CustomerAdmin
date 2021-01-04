package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Functie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface FunctieDao extends CrudRepository<Functie, Integer> {
    List<Functie> findAll();
}