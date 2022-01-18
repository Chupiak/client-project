package chupiak.service;

import chupiak.model.PersonalInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface PersonalInfoService {
    PersonalInfo add(PersonalInfo personalInfo);

    List<PersonalInfo> getAll();

    Optional<PersonalInfo> getById(Long id);
}
