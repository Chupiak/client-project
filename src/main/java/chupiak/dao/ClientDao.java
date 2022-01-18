package chupiak.dao;

import chupiak.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientDao {
    Client add(Client client);

    Long addClient(String fio, String phoneNumber);

    Optional<Client> getById(Long id);

    List<String> infoAboutClient(Long clientId);

    List<Client> getAll();
}
