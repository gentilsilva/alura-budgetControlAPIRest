package budget.control.api.domain.repository;

import budget.control.api.domain.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    Optional<?> findByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    Page<Income> findAllByActiveTrue(Pageable pageable);

    Income getReferenceByIdAndActiveTrue(Long id);

    Optional<?> findByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);

    Page<Income> findAllByActiveTrueAndDescription(Pageable pageable, String description);
}
