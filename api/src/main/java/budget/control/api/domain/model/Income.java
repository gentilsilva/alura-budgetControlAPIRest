package budget.control.api.domain.model;

import budget.control.api.domain.model.form.IncomeRegistration;
import budget.control.api.domain.model.form.IncomeUpdateData;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private Boolean active;


    public Income(IncomeRegistration incomeRegistration) {
        this.description = incomeRegistration.description();
        this.entryValue = incomeRegistration.entryValue();
        this.createAt = LocalDate.parse(incomeRegistration.createAt());
        this.active = true;
    }

    public void updateIncome(IncomeUpdateData incomeUpdateData) {
        this.description = incomeUpdateData.description();
        this.entryValue = incomeUpdateData.entryValue();
        this.createAt = LocalDate.parse(incomeUpdateData.createAt());
    }

    public void inactivateIncome() {
        this.active = false;
    }





}
