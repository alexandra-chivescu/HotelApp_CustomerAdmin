package com.proiectBD.hotel.service;


import com.proiectBD.hotel.dao.CameraDao;
import com.proiectBD.hotel.dao.Tip_cameraDao;
import com.proiectBD.hotel.model.Camera;
import com.proiectBD.hotel.model.Tip_camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class Tip_cameraService {
    @Autowired
    Tip_cameraDao tip_cameraDao;


    public List<Tip_camera> getListTypesOfRooms() {
        return tip_cameraDao.findAll();
    }

}
