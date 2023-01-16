package budget.control.api.domain.model;

import budget.control.api.domain.model.form.ExpenseRegistration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

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

    public Expense(ExpenseRegistration expenseRegistration) {
      this.description = expenseRegistration.description();
      this.entryValue = expenseRegistration.entryValue();
      this.createAt = LocalDate.parse(expenseRegistration.createAt());
    }
}
