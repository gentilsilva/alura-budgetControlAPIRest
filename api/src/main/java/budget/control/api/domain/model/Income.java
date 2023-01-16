package budget.control.api.domain.model;

import budget.control.api.domain.model.form.IncomeRegistration;
import budget.control.api.domain.repository.IncomeRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Table(name = "incomes")
@Entity(name = "Income")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Income {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal entryValue;
    private LocalDate createAt;

    public Income(IncomeRegistration incomeRegistration) {
        this.description = incomeRegistration.description();
        this.entryValue = incomeRegistration.entryValue();
        this.createAt = LocalDate.parse(incomeRegistration.createAt());
    }

}
