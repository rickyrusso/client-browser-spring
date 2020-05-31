package com.arkpes.clientbrowser.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("funds")
public class FundController {
    private FundService fundService;

    @Autowired
    public FundController(FundService fundService){
        this.fundService = fundService;
    }

    @GetMapping("/search")
    public List<Fund> getFundsByInvestor(@RequestParam("investorId") long investorId){
        try{
            return fundService.getFundsByInvestor(investorId);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
