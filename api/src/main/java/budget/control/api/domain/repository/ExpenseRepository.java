package budget.control.api.domain.repository;

import budget.control.api.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<?> findAllByDescriptionAndCreateAtBetween(String description, LocalDate dateIn, LocalDate dateOff);
}
