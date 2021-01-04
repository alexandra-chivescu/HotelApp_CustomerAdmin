package com.proiectBD.hotel.dao;

import com.proiectBD.hotel.model.Camera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CameraDao extends CrudRepository<Camera, Integer> {
    List<Camera> findAll();

    @Query(value = "SELECT c.nr_camera FROM camera c where id_tip =:id_tip",nativeQuery = true)
    List<Camera> getListCamereDeTipulDorit(int id_tip);

}