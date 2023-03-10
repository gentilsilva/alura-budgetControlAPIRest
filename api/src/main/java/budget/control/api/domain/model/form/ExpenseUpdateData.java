package budget.control.api.domain.model.form;

import budget.control.api.domain.repository.ExpenseRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public record ExpenseUpdateData(
        @NotBlank
        String description,
        @NotNull
        BigDecimal entryValue,
        @NotNull
        String createAt,

        String category

) {

        public Boolean isRepeatable(ExpenseRepository expenseRepository) {
                LocalDate dateIn = LocalDate.parse(createAt()).with(TemporalAdjusters.firstDayOfMonth());
                LocalDate dateOff = LocalDate.parse(createAt()).with(TemporalAdjusters.lastDayOfMonth());
                return expenseRepository.findAllByDescriptionAndCreateAtBetween(description(), dateIn, dateOff).isPresent();
        }

}
