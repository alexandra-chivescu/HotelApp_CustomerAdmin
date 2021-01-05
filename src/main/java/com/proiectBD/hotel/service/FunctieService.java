package com.proiectBD.hotel.service;

import com.proiectBD.hotel.dao.FunctieDao;
import com.proiectBD.hotel.model.Functie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctieService {

    @Autowired
    FunctieDao functieDao;

    public void save_new_functie(String nume_fct) {
        Functie functie = new Functie();
        functie.setNume_functie(nume_fct);
        functieDao.save(functie);
    }
}
