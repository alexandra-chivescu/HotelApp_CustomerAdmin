package com.proiectBD.hotel.service;


import com.proiectBD.hotel.dao.CameraDao;
import com.proiectBD.hotel.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CameraService {
    @Autowired
    CameraDao cameraDao;

    public List<Camera> getCartProducts(HashMap<Integer, Integer> mapCart) {
        List<Camera> list = new ArrayList<>();
        for (Integer id : mapCart.keySet()) {
            list.add(cameraDao.findById(id).get());
        }
        return list;
    }

    public List<Camera> listaCamereTipDorit(int id_tip) {
        return cameraDao.getListCamereDeTipulDorit(id_tip);
    }
    public List<Camera> getListRooms() {
        return cameraDao.findAll();
    }

}
