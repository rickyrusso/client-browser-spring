package com.arkpes.clientbrowser.fund;

import java.sql.SQLException;
import java.util.List;

public interface FundRepository {
    List<Fund> getFundsByInvestor(long investorId) throws SQLException;
}
