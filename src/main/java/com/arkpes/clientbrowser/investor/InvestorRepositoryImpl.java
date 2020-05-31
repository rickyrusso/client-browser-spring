package com.arkpes.clientbrowser.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class InvestorRepositoryImpl implements InvestorRepository{

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<Investor> getInvestorsByClient(long clientId) throws SQLException {
        String sql =
                "select I.id, I.name, count(INF.investor_id) as numOfFunds \n" +
                "from investor I\n" +
                "join client_investors CI on I.id = CI.investor_id\n" +
                "join investor_funds INF on CI.investor_id = INF.investor_id\n" +
                "where CI.client_id = :clientId \n" +
                "group by I.id, I.name";

        List<Investor> clients = jdbcTemplate.query(sql,
                new MapSqlParameterSource("clientId", clientId), (resultSet, i) -> {
                    return toInvestor(resultSet);
                });

        return clients;
    }

    private Investor toInvestor(ResultSet resultSet) throws SQLException {
        Investor investor = new Investor();
        investor.setId(resultSet.getLong("id"));
        investor.setName(resultSet.getString("name"));
        investor.setNumOfFunds(resultSet.getInt("numOfFunds"));
        return investor;
    }
}
