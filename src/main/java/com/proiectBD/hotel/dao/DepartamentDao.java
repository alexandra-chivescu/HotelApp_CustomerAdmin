package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Departament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface DepartamentDao extends CrudRepository<Departament, Integer> {
    List<Departament> findAll();

    Departament findById(int id);
}