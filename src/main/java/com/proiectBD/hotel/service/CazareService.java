package com.proiectBD.hotel.service;

import com.proiectBD.hotel.dao.CazareDao;
import com.proiectBD.hotel.model.Cazare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CazareService {
    @Autowired
    CazareDao cazareDao;

    public List<Cazare> getListCazare() {
        return cazareDao.findAll();
    }

}
