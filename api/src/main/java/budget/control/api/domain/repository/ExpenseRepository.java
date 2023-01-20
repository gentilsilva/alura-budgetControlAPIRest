package budget.control.api.domain.repository;

import budget.control.api.domain.model.Expense;
import budget.control.api.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<?> findAllByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);

    List<Expense> findAllByActiveTrue();

    Expense getReferenceByIdAndActiveTrue(Long id);

    Optional<?> findAllByDescriptionAndActiveAndCreateAtBetween(String description, Boolean isActive, LocalDate dateIn, LocalDate dateOff);
}
