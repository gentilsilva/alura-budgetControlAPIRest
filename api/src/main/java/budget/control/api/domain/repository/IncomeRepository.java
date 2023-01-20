package budget.control.api.domain.repository;

import budget.control.api.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    Optional<?> findByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    List<Income> findAllByActiveTrue();

    Income getReferenceByIdAndActiveTrue(Long id);

    Optional<?> findByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);
}
