package budget.control.api.domain.model.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import budget.control.api.domain.repository.IncomeRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IncomeRegistration(

        @NotBlank
        String description,

        @NotNull
        BigDecimal entryValue,

        @NotBlank
        String createAt

) {

        public Boolean isRepeatable(IncomeRepository incomeRepository) {
                LocalDate dateIn = LocalDate.parse(createAt()).with(TemporalAdjusters.firstDayOfMonth());
                LocalDate dateOff = LocalDate.parse(createAt()).with(TemporalAdjusters.lastDayOfMonth());
                Boolean isActive = true;
                return incomeRepository.findByDescriptionAndActiveAndCreateAtBetween(description(), isActive, dateIn, dateOff).isPresent();
        }



}
