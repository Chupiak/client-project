package chupiak.service.impl;

import chupiak.dao.NumberPhoneDao;
import chupiak.model.NumberPhone;
import chupiak.service.NumberPhoneService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberPhoneServiceImpl implements NumberPhoneService {
    private final NumberPhoneDao numberPhoneDao;

    @Autowired
    public NumberPhoneServiceImpl(NumberPhoneDao numberPhoneDao) {
        this.numberPhoneDao = numberPhoneDao;
    }

    @Override
    public NumberPhone add(NumberPhone numberPhone) {
        return numberPhoneDao.add(numberPhone);
    }

    @Override
    public List<NumberPhone> getAll() {
        return numberPhoneDao.getAll();
    }

    @Override
    public Optional<NumberPhone> getById(Long id) {
        return numberPhoneDao.getById(id);
    }
}
