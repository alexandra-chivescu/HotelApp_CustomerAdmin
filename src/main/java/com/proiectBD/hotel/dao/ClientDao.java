package com.proiectBD.hotel.dao;


import com.proiectBD.hotel.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface ClientDao extends CrudRepository<Client, Integer> {
    List<Client> findByEmail(String email);

    List<Client> findAll();

    Client findById(int id);

    void delete(Client client);
}
