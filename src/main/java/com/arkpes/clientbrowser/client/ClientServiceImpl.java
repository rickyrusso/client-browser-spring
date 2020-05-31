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
    public Client getClient(long id) throws SQLException {
        return clientRepository.getClient(id);
    }

    @Override
    public List<Client> getClients() throws SQLException {
        return clientRepository.getClients();
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.saveClient(client);
    }

}
