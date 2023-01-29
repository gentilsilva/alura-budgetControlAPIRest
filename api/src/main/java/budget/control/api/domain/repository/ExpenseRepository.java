package budget.control.api.domain.repository;

import budget.control.api.domain.model.Expense;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<?> findAllByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    Page<Expense> findAllByActiveTrue(Pageable pageable);

    Expense getReferenceByIdAndActiveTrue(Long id);

    Optional<?> findAllByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);

    Page<Expense> findAllByActiveTrueAndDescription(String description, Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.active = TRUE AND YEAR(e.createAt) = ?1 AND MONTH(e.createAt) = ?2")
    Page<Expense> findAllExpenseByActiveTrueAndYearAndMonth(Integer year, Integer month, Pageable pageable);
}
