package budget.control.api.domain.repository;

import budget.control.api.domain.model.*;
import budget.control.api.domain.model.dto.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.*;
import java.time.LocalDate;
import java.util.*;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findAllByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    Page<Expense> findAllByActiveTrue(Pageable pageable);

    Expense getReferenceByIdAndActiveTrue(Long id);

    Optional<Expense> findAllByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);

    @Query("SELECT e FROM Expense e WHERE (:description IS NULL OR e.description = :description) AND (:category IS NULL OR e.category = :category)")
    Page<Expense> findAllByOptionalFilters(String description, Category category, Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.active = TRUE AND YEAR(e.createAt) = ?1 AND MONTH(e.createAt) = ?2")
    Page<Expense> findAllExpenseByActiveTrueAndYearAndMonth(Integer year, Integer month, Pageable pageable);

    @Query("SELECT SUM(e.entryValue) FROM Expense e WHERE e.active = TRUE AND YEAR(e.createAt) = ?1 AND MONTH(e.createAt) = ?2")
    Optional<BigDecimal> readExpenseSummaryByYearAndMonth(Integer year, Integer month);

    @Query("SELECT e.category AS category, SUM(e.entryValue) AS totalValue FROM Expense e WHERE e.active = TRUE AND YEAR(e.createAt) = ?1 AND MONTH(e.createAt) = ?2 " +
            "GROUP BY e.category")
    List<CategoryBalance> readExpenseSummaryByDescriptionAndYearAndMonth(Integer year, Integer month);
}
