package budget.control.api.domain.model.form;

import budget.control.api.domain.repository.IncomeRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

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
                return incomeRepository.findByDescriptionAndCreateAtBetween(description(), dateIn, dateOff).isPresent();
        }

}
