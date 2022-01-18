package chupiak.service;

import chupiak.model.NumberPhone;
import java.util.List;
import java.util.Optional;

public interface NumberPhoneService {
    NumberPhone add(NumberPhone numberPhone);

    List<NumberPhone> getAll();

    Optional<NumberPhone> getById(Long id);

}
