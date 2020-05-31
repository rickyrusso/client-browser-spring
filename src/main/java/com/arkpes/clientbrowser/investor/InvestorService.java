package com.arkpes.clientbrowser.investor;

import java.util.List;

public interface InvestorService {
    List<Investor> getInvestorsByClient(long clientId);
}
