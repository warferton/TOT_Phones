package com.alexkirillov.telephones.dao;

import com.alexkirillov.telephones.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.util.List;

@Repository("postgres")
public class ClientDataBaseAccess implements ClientDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDataBaseAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addClient(Client client) {
        final String sql =
                "INSERT INTO clients(name, phone) VALUES('"+ client.getName() +"', '"+ client.getPhone() +"')";
        if(!getAllPhones().contains(client.getPhone()))
        {
            if(!getAllNames().contains(client.getName())) {
                jdbcTemplate.execute(sql);
                return true;
            }
        }

        return false;
    }


    @Override
    public boolean deleteClientByName(String client_name) {
        final String sql = "DELETE FROM clients WHERE name = '" + client_name +"'";
        jdbcTemplate.execute(sql);
        return true;
    }

    @Override
    public List<Client> findAllClients() {
        final String sql = "SELECT name, phone FROM clients";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            return new Client(name, phone);
        });
    }

    @Override
    public List<Client> findClientByName(@NotBlank String client_name) {
        final String sql = "SELECT name, phone FROM clients WHERE name ILIKE '%" + client_name +"%'";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            return new Client(name, phone);
        });
    }

    @Override
    public List<Client> findClientByPhone(@NotBlank String client_phone) {
        final String sql = "SELECT name, phone FROM clients WHERE phone ILIKE '%" + client_phone +"%'";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            return new Client(name, phone);
        });
    }

    @Override
    public List<String> getAllNames() {
        final String sql = "SELECT name FROM clients";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String name = resultSet.getString("name");
            return new String(name);
        });
    }

    @Override
    public List<String> getAllPhones() {
        final String sql = "SELECT phone FROM clients";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String phone = resultSet.getString("phone");
            return new String(phone);
        });
    }
}
