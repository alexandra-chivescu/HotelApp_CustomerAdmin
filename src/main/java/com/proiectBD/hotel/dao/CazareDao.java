package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Cazare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CazareDao extends CrudRepository<Cazare, Integer> {
    List<Cazare> findAll();
}