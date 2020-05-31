package com.arkpes.clientbrowser.client;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {
    Client getClient(long id) throws SQLException;
    List<Client> getClients() throws SQLException;
    void saveClient(Client client);
}
