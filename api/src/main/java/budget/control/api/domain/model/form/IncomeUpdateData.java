package budget.control.api.domain.model.form;

import budget.control.api.domain.repository.IncomeRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public record IncomeUpdateData(
        @NotBlank
        String description,
        @NotNull
        BigDecimal entryValue,
        @NotNull
        String createAt
) {

        public Boolean isRepeatable(IncomeRepository incomeRepository) {
                LocalDate dateIn = LocalDate.parse(createAt()).with(TemporalAdjusters.firstDayOfMonth());
                LocalDate dateOff = LocalDate.parse(createAt()).with(TemporalAdjusters.lastDayOfMonth());
                return incomeRepository.findByDescriptionAndCreateAtBetween(description(), dateIn, dateOff).isPresent();
        }

}
