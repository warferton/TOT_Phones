package com.alexkirillov.telephones.service;

import com.alexkirillov.telephones.dao.ClientDao;
import com.alexkirillov.telephones.dao.ClientFakeDataAccess;
import com.alexkirillov.telephones.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientDao clientDao;
    @Autowired
    public ClientService(@Qualifier("postgres") ClientDao client_access){
        clientDao = client_access;
    }

    public boolean addClient(Client client){
        return clientDao.addClient(client);
    }

    public boolean deleteClient(String client_name){
        return clientDao.deleteClientByName(client_name);
    }

    public List<Client> findAll(){ return clientDao.findAllClients();}

    public List<Client> findClients(String client_name){
        return clientDao.findClientByName(client_name);
    }
}
