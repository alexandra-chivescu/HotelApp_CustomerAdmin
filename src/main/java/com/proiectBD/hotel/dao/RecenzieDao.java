package com.proiectBD.hotel.dao;



import com.proiectBD.hotel.model.Recenzie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface RecenzieDao extends CrudRepository<Recenzie, Integer> {
    List<Recenzie> findAll();
}