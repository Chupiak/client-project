package chupiak.service.impl;

import chupiak.dao.PersonalInfoDao;
import chupiak.model.PersonalInfo;
import chupiak.service.PersonalInfoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {
    private final PersonalInfoDao personalInfoDao;

    @Autowired
    public PersonalInfoServiceImpl(PersonalInfoDao personalInfoDao) {
        this.personalInfoDao = personalInfoDao;
    }

    @Override
    public PersonalInfo add(PersonalInfo personalInfo) {
        return personalInfoDao.add(personalInfo);
    }

    @Override
    public List<PersonalInfo> getAll() {
        return personalInfoDao.getAll();
    }

    @Override
    public Optional<PersonalInfo> getById(Long id) {
        return personalInfoDao.getById(id);
    }
}
