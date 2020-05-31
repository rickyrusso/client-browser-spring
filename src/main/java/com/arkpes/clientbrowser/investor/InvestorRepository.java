package com.arkpes.clientbrowser.investor;

import java.sql.SQLException;
import java.util.List;

public interface InvestorRepository {
    List<Investor> getInvestorsByClient(long clientId) throws SQLException;
}
