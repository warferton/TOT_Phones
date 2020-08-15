package com.alexkirillov.telephones.dao;

import com.alexkirillov.telephones.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeDB")
public class ClientFakeDataAccess implements ClientDao {
    private static List<Client> client_data_base;

    @Autowired
    public ClientFakeDataAccess() {
        //in-memory data base implementation
        client_data_base = new ArrayList<>();
//        client_data_base.add(new Client("Jacke Haul", "123-857-7293"));
//        client_data_base.add(new Client("Nick Kyekr", "129-945-6353"));
//        client_data_base.add(new Client("Many Loe", "122-356-7973"));
//        client_data_base.add(new Client("Colin Paul", "123-857-7293"));
//        client_data_base.add(new Client("Xavier Aiden", "123-854-7599"));
//        client_data_base.add(new Client("Koers McKenzie", "128-800-4583"));
    }

    /**
     *Adds a new <b>client</b> to the Data Base. Checks if a client with
     * simmilar phone number or name exists, and if not adds new client.
     * @param client - a Client object
     * @return 1 - if a new client was added successfully. 2 - if a client has an
     *              already used phone or name.
     */
    @Override
    public boolean addClient(Client client) {
        if(!getAllPhones().contains(client.getPhone()))
        {
            if(!getAllNames().contains(client.getName())) {
                client_data_base.add(client);
                return true;
            }
        }

        return false;
    }

    /**
     * Deletes a <b>client</b> from a DB.
     * @param client_name - name of a client
     * @return 0 - if Exception occurs, 1 - if deleted successfully.
     */
    @Override
    public boolean deleteClientByName(String client_name) {
        Client client_to_delete = findClientByName(client_name).get(0);
        client_data_base.remove(client_to_delete);
        return true;
    }

    @Override
    public List<Client> findAllClients(){
        return client_data_base;
    }

    /**
     * Function utilizes Java.stream() to scan through the list
     * and find every person that suits criteria of having the same name,
     * returns list of clients whos name that starts with the <i>client_name</i> String.
     * @param client_name - <b>name</b> or <b>prefix</b> of some clients name
     * @return List<Client>
     */
    @Override
    public List<Client> findClientByName(String client_name) {
        return client_data_base.stream().distinct()
                        .filter(a -> a.getName().toLowerCase()
                        .startsWith(client_name.toLowerCase()))
                        .collect(Collectors.toList());
    }

    /**function similar to findClientByName()
    * but utilizes clients phone instead.
    * Could be used for better client data
    * checking before adding a new client
    * so the new clients data will not
    * be identical to already existing ones.
    * Basically, for checking if such phone number
    * is already used by an existing client
    * @param client_phone - clients phone
    * @return List<Client>.
    * */
    @Override
    public List<Client> findClientByPhone(String client_phone) {
        return client_data_base.stream().distinct()
                .filter(a -> a.getPhone().contains(client_phone))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all phone numbers of all clients stored in the database.
     * @return List of phone numbers
     *
     */
    public List<String> getAllPhones(){
        return client_data_base.stream().distinct()
                .map(a -> {return a.getPhone();}).collect(Collectors.toList());
    }

    /**
     * Retrieves all names of all clients stored in the database.
     * @return List of phone numbers
     *
     */
    public List<String> getAllNames(){
        return client_data_base.stream().distinct()
                .map(a -> {return a.getName();}).collect(Collectors.toList());
    }
}
