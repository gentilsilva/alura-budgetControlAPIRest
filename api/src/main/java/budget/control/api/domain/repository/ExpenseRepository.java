package budget.control.api.domain.repository;

import budget.control.api.domain.model.Category;
import budget.control.api.domain.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<?> findAllByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    Page<Expense> findAllByActiveTrue(Pageable pageable);

    Expense getReferenceByIdAndActiveTrue(Long id);

    Optional<?> findAllByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);

    Page<Expense> findAllByActiveTrueAndDescriptionOrCategory(String description, Category category, Pageable pageable);
}
