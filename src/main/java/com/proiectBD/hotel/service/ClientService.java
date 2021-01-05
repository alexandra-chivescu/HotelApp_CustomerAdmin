package com.proiectBD.hotel.service;

import com.proiectBD.hotel.dao.ClientDao;
import com.proiectBD.hotel.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientDao clientDao;

    public void save(String email, String parola, String nume, String prenume) {
        Client client = new Client();
        client.setNume(nume);
        client.setPrenume(prenume);
        client.setEmail(email);
        client.setParola(parola);
        client.setCont_online("da");
        clientDao.save(client);
    }

    public void save_new_client(String nume, String prenume, String cnp, String telefon, String email) {
        Client client = new Client();
        client.setNume(nume);
        client.setPrenume(prenume);
        client.setCnp(cnp);
        client.setTelefon(telefon);
        client.setEmail(email);
        client.setCont_online("nu");
        clientDao.save(client);
    }

    public void modifyClientById(int id, String nume, String prenume, String cnp, String telefon, String email ) {
        Client client_update = clientDao.findById(id);
        client_update.setNume(nume);
        client_update.setPrenume(prenume);
        client_update.setCnp(cnp);
        client_update.setTelefon(telefon);
        client_update.setEmail(email);
        clientDao.save(client_update);
        //aici trebuie sa modific si in baza de date
    }

    public List<Client> getUsersByEmail(String email) {
        return clientDao.findByEmail(email);
    }


}
