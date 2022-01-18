package chupiak.dao;

import chupiak.exeptional.DataProcessingException;
import chupiak.model.NumberPhone;
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
public class NumberPhoneDaoImpl implements NumberPhoneDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public NumberPhoneDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public NumberPhone add(NumberPhone numberPhone) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            numberPhone.setCreated(LocalDateTime.now());
            if (numberPhone.getClientId() == null) {
                transaction.rollback();
            }
            session.save(numberPhone);
            transaction.commit();
            session.close();
            return numberPhone;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert numberPhone without clientID: "
                    + numberPhone, e);
        }
    }

    @Override
    public List<NumberPhone> getAll() {
        Session session;
        try {
            session = sessionFactory.openSession();
            CriteriaQuery<NumberPhone> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(NumberPhone.class);
            criteriaQuery.from(NumberPhone.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all numbers phone", e);
        }
    }

    @Override
    public Optional<NumberPhone> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<NumberPhone> query = session.createQuery(""
                    + "FROM NumberPhone np WHERE np.id = :id", NumberPhone.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a movie session by id: " + id, e);
        }
    }

}


