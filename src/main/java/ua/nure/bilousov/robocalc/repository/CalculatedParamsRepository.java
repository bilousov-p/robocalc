package ua.nure.bilousov.robocalc.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;

import java.util.Optional;

public interface CalculatedParamsRepository extends CrudRepository<CalculatedParams, Long> {

    @Override
    Optional<CalculatedParams> findById(Long aLong);
}
