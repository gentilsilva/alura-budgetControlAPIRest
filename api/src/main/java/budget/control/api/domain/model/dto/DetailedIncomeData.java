package budget.control.api.domain.model.dto;

import budget.control.api.domain.model.Income;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record DetailedIncomeData(
        Long id,
        String description,
        BigDecimal entryValue,
        LocalDate createAt
) {

    public DetailedIncomeData(Income income) {
        this(income.getId(), income.getDescription(), income.getEntryValue(), income.getCreateAt());
    }
}
