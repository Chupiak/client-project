package chupiak.dao;

import chupiak.model.PersonalInfo;
import java.util.List;
import java.util.Optional;

public interface PersonalInfoDao {
    PersonalInfo add(PersonalInfo personalInfo);

    List<PersonalInfo> getAll();

    Optional<PersonalInfo> getById(Long id);
}
