package com.arkpes.clientbrowser.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientInvestments> getClients() {
        return clientRepository.getClients();
    }

    @Override
    public void saveClient(Client client) {
        if(client.getId() <= 0)
            throw new InvalidClientDataException("ID is invalid");

        if(client.getName() == null || client.getName() == "")
            throw new InvalidClientDataException("Name is required");

        if(client.getDescription() == null || client.getDescription() == "")
            throw new InvalidClientDataException("Description is required");

        clientRepository.saveClient(client);
    }

}
