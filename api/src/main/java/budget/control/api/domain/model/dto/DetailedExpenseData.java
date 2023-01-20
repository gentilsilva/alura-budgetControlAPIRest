package budget.control.api.domain.model.dto;

import budget.control.api.domain.model.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetailedExpenseData(
        String description,
        BigDecimal entryValue,
        LocalDate createAt
) {

    public DetailedExpenseData(Expense expense) {
        this(expense.getDescription(), expense.getEntryValue(), expense.getCreateAt());
    }

}
