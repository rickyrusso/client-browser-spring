package com.arkpes.clientbrowser.fund;

import com.arkpes.clientbrowser.investor.Investor;
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
public class FundRepositoryImpl implements FundRepository {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Fund> getFundsByInvestor(long investorId) throws SQLException {
        String sql =
            "select F.id, F.name\n" +
            "from fund F\n" +
            "join investor_funds INF on F.id = INF.fund_id\n" +
            "where INF.investor_id = :investorId";

        List<Fund> clients = jdbcTemplate.query(sql,
                new MapSqlParameterSource("investorId", investorId), (resultSet, i) -> {
                    return toFund(resultSet);
                });

        return clients;
    }

    private Fund toFund(ResultSet resultSet) throws SQLException{
        Fund fund = new Fund();
        fund.setId(resultSet.getLong("id"));
        fund.setName(resultSet.getString("name"));
        return fund;
    }
}
