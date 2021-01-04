package com.proiectBD.hotel.dao;


import com.proiectBD.hotel.model.Tip_camera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface Tip_cameraDao extends CrudRepository<Tip_camera, Integer> {
    List<Tip_camera> findAll();

}