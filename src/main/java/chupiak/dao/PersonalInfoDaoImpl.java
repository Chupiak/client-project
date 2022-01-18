package chupiak.dao;

import chupiak.exeptional.DataProcessingException;
import chupiak.model.PersonalInfo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalInfoDaoImpl implements PersonalInfoDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonalInfoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PersonalInfo add(PersonalInfo personalInfo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            personalInfo.setCreated(LocalDateTime.now());
            if (personalInfo.getClientId() == null) {
                transaction.rollback();
            }
            session.save(personalInfo);
            transaction.commit();
            session.close();
            return personalInfo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can't insert personal info without clientID: " + personalInfo, e);
        }
    }

    @Override
    public List<PersonalInfo> getAll() {
        Session session;
        try {
            session = sessionFactory.openSession();
            CriteriaQuery<PersonalInfo> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(PersonalInfo.class);
            criteriaQuery.from(PersonalInfo.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all numbers phone", e);
        }
    }

    @Override
    public Optional<PersonalInfo> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<PersonalInfo> query = session.createQuery(
                    "FROM PersonalInfo pi WHERE pi.id = :id", PersonalInfo.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a movie session by id: " + id, e);
        }
    }

}
