package com.arkpes.clientbrowser.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<ClientInvestments> getClients(){
        String sql =
            "select c.id, c.name, c.description, count(CI.client_id) as mum_of_investments\n" +
            "from client C\n" +
            "join client_investors CI on c.id = CI.client_id\n" +
            "group by c.name, c.description";

        List<ClientInvestments> clients = jdbcTemplate.query(sql, (resultSet, i) -> {
            return toClientInvestment(resultSet);
        });

        return clients;
    }

    @Override
    public void saveClient(Client client) {
        String sql =
            "update client set name = :name, description = :description where id = :clientId";

        MapSqlParameterSource params = new MapSqlParameterSource("clientId", client.getId())
                .addValue("name", client.getName())
                .addValue("description", client.getDescription());
        jdbcTemplate.update(sql, params);
    }


    private ClientInvestments toClientInvestment(ResultSet resultSet) throws SQLException{
        ClientInvestments client = new ClientInvestments();
        client.setId(resultSet.getLong("id"));
        client.setName(resultSet.getString("name"));
        client.setDescription(resultSet.getString("description"));
        client.setNumOfInvestments(resultSet.getInt("mum_of_investments"));
        return client;
    }
}
