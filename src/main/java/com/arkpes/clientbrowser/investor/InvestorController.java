package com.arkpes.clientbrowser.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("investors")
public class InvestorController {
    private InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService){
        this.investorService = investorService;
    }

    @GetMapping("/search")
    public List<Investor> getInvestorsByClient(@RequestParam("clientId") long clientId){
        try{
            return investorService.getInvestorsByClient(clientId);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
