package com.arkpes.clientbrowser.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvestorServiceImpl implements InvestorService {
    private  InvestorRepository investorRepository;

    @Autowired
    public InvestorServiceImpl(InvestorRepository investorRepository){
        this.investorRepository = investorRepository;
    }

    @Override
    public List<Investor> getInvestorsByClient(long clientId) {
        try {
            return investorRepository.getInvestorsByClient(clientId);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }
}
