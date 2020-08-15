package com.alexkirillov.telephones.dao;

import com.alexkirillov.telephones.model.Client;
import com.alexkirillov.telephones.service.ClientService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class ClientServiceTest {

    private ClientFakeDataAccess caccs = new ClientFakeDataAccess();
    private ClientService clientService;

    private Validator validator;

    @BeforeEach
    void initService(){
        clientService = new ClientService(caccs);
    }

    @Test
    void constraintsTest(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Client client_2 = new Client("", "111-111-1111");
        Client client_3 = new Client("Name Kole", "312-214-124");

        Set<ConstraintViolation<Client>> violations = validator.validate(client_2);
        assertFalse(violations.isEmpty());

        violations = validator.validate(client_3);
        assertFalse(violations.isEmpty());
    }

    @Test
    void addClient(){
        Client client_1 = new Client("Name Name", "222-222-2222");
        Client client_1_1 = new Client("Name Name", "212-121-2123");
        Client client_1_2 = new Client("Name Fame", "222-222-2222");
        assertEquals
                ("Adding new client", true , clientService.addClient(client_1));
        assertEquals
                ("Add client with existing name:", false, clientService.addClient(client_1_1));
        assertEquals
                ("Add client with existing phone", false, clientService.addClient(client_1_2));
    }

    @Test
    void findAll(){
        Client client_1 =
                new Client("Name Name", "222-222-2222");
        Client client_2 =
                new Client("Name2 Name2", "211-211-2111");
        Client client_3 =
                new Client("Name3 Name3", "333-332-3332");

        List<Client> compareList = new ArrayList<>();
        compareList.add(client_1);
        compareList.add(client_2);
        compareList.add(client_3);

        clientService.addClient(client_1);
        clientService.addClient(client_2);
        clientService.addClient(client_3);

        List<Client> resultList = clientService.findAll();
        assertThat(resultList).isNotNull();
        assertEquals("Outputting all clients", compareList, resultList);
    }

    @Test
    void findClients(){
        Client client_1 =
                new Client("Name Name", "222-222-2222");
        Client client_2 =
                new Client("Name2 Name2", "211-211-2111");
        Client client_3 =
                new Client("Name3 Name3", "333-332-3332");

        clientService.addClient(client_1);
        clientService.addClient(client_2);
        clientService.addClient(client_3);

        Client resultClient = clientService.findClients("Name N").get(0);

        assertEquals("Check find client", client_1, resultClient);
        assertEquals("No client found", Collections.emptyList(), clientService.findClients("op"));
    }

    @Test
    void deleteClient(){
        Client client_1 =
                new Client("Name Name", "222-222-2222");
        Client client_2 =
                new Client("Name2 Name2", "211-211-2111");
        Client client_3 =
                new Client("Name3 Name3", "333-332-3332");

        clientService.addClient(client_1);
        clientService.addClient(client_2);
        clientService.addClient(client_3);

        Client[] compareArray = new Client[]{client_2, client_3};

        assertEquals
                ("Deleting Client", true, clientService.deleteClient(client_1.getName()));
        assertEquals("Renewed List", compareArray, clientService.findAll().toArray());

    }


}
