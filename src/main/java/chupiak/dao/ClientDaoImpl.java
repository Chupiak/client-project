package chupiak.dao;

import chupiak.exeptional.DataProcessingException;
import chupiak.model.Client;
import chupiak.model.NumberPhone;
import chupiak.model.PersonalInfo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl implements ClientDao {
    private final SessionFactory sessionFactory;
    private final NumberPhoneDao numberPhoneDao;
    private final PersonalInfoDao personalInfoDao;

    @Autowired
    public ClientDaoImpl(SessionFactory sessionFactory,
                         NumberPhoneDao numberPhoneDao, PersonalInfoDao personalInfoDao) {
        this.sessionFactory = sessionFactory;
        this.numberPhoneDao = numberPhoneDao;
        this.personalInfoDao = personalInfoDao;
    }

    @Override
    public Client add(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            client.setCreated(LocalDateTime.now());
            session.save(client);
            transaction.commit();
            session.close();
            return client;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert client: " + client, e);
        }
    }

    @Override
    public Long addClient(String fio, String phoneNumber) {
        List<NumberPhone> allPhoneNumber = numberPhoneDao.getAll();
        boolean phoneResult = allPhoneNumber.stream().distinct()
                .anyMatch(x -> x.getPhoneNumber().equals(phoneNumber));
        if (!phoneResult) {
            Client client = new Client();
            add(client);
            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setClientId(client);
            personalInfo.setFio(fio);
            personalInfoDao.add(personalInfo);

            NumberPhone numberPhone = new NumberPhone();
            numberPhone.setClientId(client);
            numberPhone.setPhoneType(1);
            numberPhone.setPhoneNumber(phoneNumber);
            numberPhoneDao.add(numberPhone);
            return client.getId();
        } else {
            for (NumberPhone numberPhone : allPhoneNumber) {
                if (numberPhone.getPhoneNumber().equals(phoneNumber)) {
                    return numberPhone.getClientId().getId();
                }
            }
            return (Long) Optional.empty().get();
        }
    }

    @Override
    public Optional<Client> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery(
                    "FROM Client cl WHERE cl.id = :id AND deleted = null ", Client.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a movie session by id: " + id, e);
        }
    }

    @Override
    public List<String> infoAboutClient(Long clientId) {
        try {
            List<String> infoClient = new ArrayList<>();
            infoClient.add("CientID=" + (getById(clientId).get().getId().toString()));
            infoClient.add(("Phone" + numberPhoneDao.getById(clientId).get().getPhoneNumber()));
            infoClient.add("PhoneType=" + (numberPhoneDao.getById(clientId).get().getPhoneType()));
            infoClient.add(("FIO " + personalInfoDao.getById(clientId).get().getFio()));
            infoClient.add("PassportData " + (personalInfoDao.getById(clientId)
                    .get().getPassportData()));
            infoClient.add("Birhday " + (personalInfoDao.getById(clientId).get().getBirthday()));
            return infoClient;
        } catch (Exception e) {
            throw new DataProcessingException("Client not found",e);
        }
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
