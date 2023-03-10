package budget.control.api.domain.repository;

import budget.control.api.domain.model.Income;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.*;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    Optional<Income> findByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    Page<Income> findAllByActiveTrue(Pageable pageable);

    Income getReferenceByIdAndActiveTrue(Long id);

    Optional<Income> findByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);

    Page<Income> findAllByActiveTrueAndDescription(String description, Pageable pageable);

    @Query("SELECT i FROM Income i WHERE i.active = TRUE AND YEAR(i.createAt) = ?1 AND MONTH(i.createAt) = ?2")
    Page<Income> findAllIncomeByActiveTrueAndYearAndMonth(Integer year, Integer month, Pageable pageable);

    @Query("SELECT SUM(i.entryValue) FROM Income i WHERE i.active = TRUE AND YEAR(i.createAt) = ?1 AND MONTH(i.createAt) = ?2")
    Optional<BigDecimal> findSummaryByYearAndMonth(Integer year, Integer month);
}
