package budget.control.api.domain.model.dto;

import budget.control.api.domain.model.Income;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetailedIncomeData(
        String description,
        BigDecimal entryValue,
        LocalDate createAt
) {

    public DetailedIncomeData(Income income) {
        this(income.getDescription(), income.getEntryValue(), income.getCreateAt());
    }
}
