package com.proiectBD.hotel.service;

import com.proiectBD.hotel.dao.RezervareDao;
import com.proiectBD.hotel.model.Rezervare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RezervareService {

    @Autowired
    RezervareDao rezervareDao;

    public void save(String checkInDate, String checkOutDate, Integer idRoom, Integer idUser, Integer nradulti, Integer nrcopii, Integer nrpatsuplimentar) {
        Rezervare rezervare = new Rezervare();
        rezervare.setZi_sosire(checkInDate);
        rezervare.setZi_plecare(checkOutDate);
        rezervare.setId_rezervare(idRoom);
        rezervare.setId_client(idUser);
        rezervare.setNr_adulti(nradulti);
        rezervare.setNr_copii(nrcopii);
        rezervare.setPat_suplimentar(nrpatsuplimentar);
        rezervareDao.save(rezervare);
    }

    public List<Rezervare> getListReservations() {
        return rezervareDao.findAll();
    }

    public List<Rezervare> findRezervariTipDorit(int id_tip_cautat) {
        return rezervareDao.findRezervareTipCautat(id_tip_cautat);
    }
}
