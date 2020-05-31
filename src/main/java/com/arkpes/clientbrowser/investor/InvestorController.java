package com.arkpes.clientbrowser.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return investorService.getInvestorsByClient(clientId);
    }

}
