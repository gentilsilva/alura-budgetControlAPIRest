package budget.control.api.domain.repository;

import budget.control.api.domain.model.Income;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    Optional<?> findByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    Page<Income> findAllByActiveTrue(Pageable pageable);

    Income getReferenceByIdAndActiveTrue(Long id);

    Optional<?> findByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);

    Page<Income> findAllByActiveTrueAndDescription(String description, Pageable pageable);

    @Query("SELECT i FROM Income i WHERE i.active = TRUE And YEAR(i.createAt) = ?1 AND MONTH(i.createAt) = ?2")
    Page<Income> findAllIncomeByActiveTrueAndYearAndMonth(Integer year, Integer month, Pageable pageable);
}
