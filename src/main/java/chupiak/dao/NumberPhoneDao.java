package chupiak.dao;

import chupiak.model.NumberPhone;
import java.util.List;
import java.util.Optional;

public interface NumberPhoneDao {
    NumberPhone add(NumberPhone numberPhone);

    List<NumberPhone> getAll();

    Optional<NumberPhone> getById(Long id);
}
