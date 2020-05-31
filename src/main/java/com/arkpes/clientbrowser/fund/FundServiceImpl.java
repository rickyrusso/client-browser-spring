package com.arkpes.clientbrowser.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FundServiceImpl implements FundService {
    private FundRepository fundRepository;

    @Autowired
    public  FundServiceImpl(FundRepository fundRepository){
        this.fundRepository = fundRepository;
    }

    @Override
    public List<Fund> getFundsByInvestor(long investorId) throws SQLException {
        return fundRepository.getFundsByInvestor(investorId);
    }
}
