package budget.control.api.domain.model;

import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
import budget.control.api.domain.service.ExpenseRegistrationService;
import budget.control.api.domain.service.ExpenseUpdateDataService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "expenses")
@Entity(name = "Expense")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Expense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal entryValue;
    private LocalDate createAt;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Expense(ExpenseRegistration expenseRegistration, ExpenseRegistrationService expenseRegistrationService) {
      this.description = expenseRegistration.description().toUpperCase();
      this.entryValue = expenseRegistration.entryValue();
      this.createAt = LocalDate.parse(expenseRegistration.createAt());
      this.active = true;
      this.category = expenseRegistrationService.isNull(expenseRegistration);
    }

    public void updateExpense(ExpenseUpdateData expenseUpdateData, ExpenseUpdateDataService expenseUpdateDataService) {
        this.description = expenseUpdateData.description().toUpperCase();
        this.entryValue = expenseUpdateData.entryValue();
        this.createAt = LocalDate.parse(expenseUpdateData.createAt());
        this.category = expenseUpdateDataService.isNull(expenseUpdateData);
    }

    public void inactivateExpense() {
        this.active = false;
    }
}
