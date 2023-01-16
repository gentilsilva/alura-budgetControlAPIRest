package budget.control.api.domain.datas;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BudgetData {

    private String description;
    @JoinColumn(name = "entry_value")
    private BigDecimal entryValue;
    @JoinColumn(name = "create_at")
    private LocalDate createAt;

}
