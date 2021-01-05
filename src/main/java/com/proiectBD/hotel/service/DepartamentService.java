package com.proiectBD.hotel.service;

import com.proiectBD.hotel.dao.DepartamentDao;
import com.proiectBD.hotel.model.Departament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentService {

    @Autowired
    DepartamentDao departamentDao;

    public void save_new_departament(String nume_dept) {
        Departament departament = new Departament();
        departament.setNume_departament(nume_dept);
        departamentDao.save(departament);
    }
}
