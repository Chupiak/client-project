package chupiak.service;

import chupiak.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client add(Client client);

    Long addClient(String fio, String phoneNumber);

    Optional<Client> getById(Long id);

    List<String> infoAboutClient(Long clientId);

    List<Client> getAll();
}
