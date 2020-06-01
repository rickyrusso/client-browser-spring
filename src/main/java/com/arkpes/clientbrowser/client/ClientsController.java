package com.arkpes.clientbrowser.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientsController {
    private ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientInvestments>  getClients(){
        return clientService.getClients();
    }

    @PostMapping
    public void postClient(@RequestBody Client client){
        clientService.saveClient(client);
    }

}
