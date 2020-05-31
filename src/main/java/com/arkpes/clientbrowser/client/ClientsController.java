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

    @GetMapping("/{id}")
    public Client getClient(@PathVariable long id){
        try {
            return clientService.getClient(id);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping
    public List<Client>  getClients(){
        try {
            return clientService.getClients();
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping
    public void postClient(@RequestBody Client client){
        try {
        clientService.saveClient(client);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
