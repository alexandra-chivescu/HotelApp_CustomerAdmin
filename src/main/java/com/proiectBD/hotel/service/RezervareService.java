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

    public void save_new_rezervare(int id_client, String zi_sosire, String zi_plecare, Float plata_avans, int nr_adulti, int nr_copii, int pat_suplimentar) {
        Rezervare rezervare = new Rezervare();
        rezervare.setId_client(id_client);
        rezervare.setZi_sosire(zi_sosire);
        rezervare.setZi_plecare(zi_plecare);
        rezervare.setPlata_avans(plata_avans);
        rezervare.setNr_adulti(nr_adulti);
        rezervare.setNr_copii(nr_copii);
        rezervare.setPat_suplimentar(pat_suplimentar);
        rezervareDao.save(rezervare);
    }

    public List<Rezervare> getListReservations() {
        return rezervareDao.findAll();
    }

    public List<Rezervare> findRezervariTipDorit(int id_tip_cautat) {
        return rezervareDao.findRezervareTipCautat(id_tip_cautat);
    }
}
