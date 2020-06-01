package com.arkpes.clientbrowser.client;

import java.sql.SQLException;
import java.util.List;

public interface ClientRepository {
    List<ClientInvestments> getClients();
    void saveClient(Client client);
}
