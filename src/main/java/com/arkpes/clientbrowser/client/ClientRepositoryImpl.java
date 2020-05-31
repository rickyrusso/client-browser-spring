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
    public Client getClient(long id) throws SQLException {
        String sql = "select * from client where id =:id";
        List<Client> clients = jdbcTemplate.query(sql,
                new MapSqlParameterSource("id", id), (resultSet, i) -> {
                    return toClient(resultSet, true);
                });

        if(clients.size() == 1){
            return clients.get(0);
        }

        return null;
    }

    @Override
    public List<Client> getClients() throws SQLException{
        String sql =
            "select c.id, c.name, c.description, count(CI.client_id) as mum_of_investments\n" +
            "from client C\n" +
            "join client_investors CI on c.id = CI.client_id\n" +
            "group by c.name, c.description";

        List<Client> clients = jdbcTemplate.query(sql, (resultSet, i) -> {
            return toClient(resultSet, false);
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


    private Client toClient(ResultSet resultSet, boolean skipNumInvestments) throws SQLException{
        Client client = new Client();
        client.setId(resultSet.getLong("id"));
        client.setName(resultSet.getString("name"));
        client.setDescription(resultSet.getString("description"));

        if( !skipNumInvestments )
            client.setNumOfInvestments(resultSet.getInt("mum_of_investments"));

        return client;
    }
}
