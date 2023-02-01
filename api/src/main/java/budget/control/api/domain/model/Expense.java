package budget.control.api.domain.model;

import budget.control.api.domain.model.converter.CategoryConverter;
import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal entryValue;
	private LocalDate createAt;
	private Boolean active;

	private Category category;
	

	public Expense(ExpenseRegistration expenseRegistration, CategoryConverter categoryConverter) {
      this.description = expenseRegistration.description().toUpperCase();
      this.entryValue = expenseRegistration.entryValue();
      this.createAt = LocalDate.parse(expenseRegistration.createAt());
      this.active = true;
      this.category = categoryConverter.convertToEntityAttribute(expenseRegistration.category());
    }

	public void updateExpense(ExpenseUpdateData expenseUpdateData, CategoryConverter categoryConverter) {
		this.description = expenseUpdateData.description().toUpperCase();
		this.entryValue = expenseUpdateData.entryValue();
		this.createAt = LocalDate.parse(expenseUpdateData.createAt());
		this.category = categoryConverter.convertToEntityAttribute(expenseUpdateData.category());
	}

	public void inactivateExpense() {
		this.active = false;
	}
}
