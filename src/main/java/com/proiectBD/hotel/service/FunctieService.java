package com.proiectBD.hotel.service;

import com.proiectBD.hotel.dao.FunctieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctieService {

    @Autowired
    FunctieDao functieDao;
}
