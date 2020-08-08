package com.alexkirillov.telephones.dao;

import com.alexkirillov.telephones.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeDB")
public class ClientDataAccess implements ClientDao {
    private static List<Client> client_data_base;

    @Autowired
    public ClientDataAccess() {
        client_data_base = new ArrayList<>();
        client_data_base.add(new Client("Jacke Haul", "+1238577293"));
        client_data_base.add(new Client("Nick Kyekr", "+1299456353"));
        client_data_base.add(new Client("Many Loe", "+12235679973"));
        client_data_base.add(new Client("Colin Paul", "+1238577293"));
        client_data_base.add(new Client("Xavier Aiden", "+12385475993"));
        client_data_base.add(new Client("Koers McKenzie", "+12388004583"));
    }

    @Override
    public int addClient(Client client) {
        if(client_data_base.contains(client))
            return 0;
        client_data_base.add(client);
        return 1;
    }

    @Override
    public int deleteClient(Client client) {
        if(client_data_base.contains(client))
        {
            client_data_base.remove(client);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Client> findClientByName(String client_name) {
        return client_data_base.stream().distinct()
                        .filter(a -> a.getName().toLowerCase()
                        .contains(client_name.toLowerCase()))
                        .collect(Collectors.toList());
    }
}
