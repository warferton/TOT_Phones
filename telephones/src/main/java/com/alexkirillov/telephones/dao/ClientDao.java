package com.alexkirillov.telephones.dao;

import com.alexkirillov.telephones.model.Client;

import java.util.List;

public interface ClientDao {
    public int addClient(Client client);

    public int deleteClient(Client client);

    public List<Client> findClientByName(String client_name);

   // public List<Client> findClientByPhone(String client_phone);
}
