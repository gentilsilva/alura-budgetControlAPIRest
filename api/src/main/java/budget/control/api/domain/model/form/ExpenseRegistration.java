package budget.control.api.domain.model.form;

import budget.control.api.domain.repository.ExpenseRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public record ExpenseRegistration(

        @NotBlank
        String description,

        @NotNull
        BigDecimal entryValue,

        @NotBlank
        String createAt
) {

    public Boolean isRepeatable(ExpenseRepository expenseRepository) {
        LocalDate dateIn = LocalDate.parse(createAt()).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate dateOff = LocalDate.parse(createAt()).with(TemporalAdjusters.lastDayOfMonth());
        Boolean isActive = true;
        return expenseRepository.findAllByDescriptionAndActiveAndCreateAtBetween(description(), isActive, dateIn, dateOff).isPresent();
    }
}
