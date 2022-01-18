package chupiak.service.impl;

import chupiak.dao.ClientDao;
import chupiak.model.Client;
import chupiak.service.ClientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client add(Client client) {
        return clientDao.add(client);
    }

    @Override
    public Long addClient(String fio, String phoneNumber) {
        return clientDao.addClient(fio,phoneNumber);
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientDao.getById(id);
    }

    @Override
    public List<String> infoAboutClient(Long clientId) {
        return clientDao.infoAboutClient(clientId);
    }

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }
}
