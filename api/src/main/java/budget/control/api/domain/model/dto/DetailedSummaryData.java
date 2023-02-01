package budget.control.api.domain.model.dto;


import java.math.*;
import java.util.*;

public record DetailedSummaryData(
        BigDecimal incomeTotalEntry,

        BigDecimal expenseTotalEntry,

        BigDecimal monthBalance,

        List<CategoryBalance> categoryBalance
) {
    public DetailedSummaryData(Optional<BigDecimal> incomeSummary, Optional<BigDecimal> expenseSummary, List<CategoryBalance> categorySummary) {
        this(incomeSummary.orElse(BigDecimal.ZERO),
                expenseSummary.orElse(BigDecimal.ZERO),
                incomeSummary.orElse(BigDecimal.ZERO).subtract(expenseSummary.orElse(BigDecimal.ZERO)),
                categorySummary.stream().toList()
        );
    }

}
