package com.alexkirillov.telephones.dao;

import com.alexkirillov.telephones.model.Client;

import java.util.List;

public interface ClientDao {
    public boolean addClient(Client client);

    public boolean deleteClientByName(String client_name);

    public List<Client> findAllClients();

    public List<Client> findClientByName(String client_name);

    public List<Client> findClientByPhone(String client_phone);

    public  List<String> getAllNames();

    public List<String> getAllPhones();
}
