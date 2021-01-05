package com.proiectBD.hotel.service;

import com.proiectBD.hotel.dao.Detalii_cameraDao;
import com.proiectBD.hotel.model.Detalii_camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Detalii_cameraService {

    @Autowired
    Detalii_cameraDao detalii_cameraDao;

    public void save_new_det_camera(String tip, float pret, int capacitate) {
        Detalii_camera detalii_camera = new Detalii_camera();
        detalii_camera.setTip(tip);
        detalii_camera.setCapacitate(capacitate);
        detalii_camera.setPret(pret);
        detalii_cameraDao.save(detalii_camera);
    }
}
