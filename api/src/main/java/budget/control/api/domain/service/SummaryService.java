package budget.control.api.domain.service;

import budget.control.api.domain.model.dto.*;
import budget.control.api.domain.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Service
public class SummaryService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    IncomeRepository incomeRepository;

    public ResponseEntity<?> readIncomeSummaryByYearAndMonth(Integer year, Integer month) {
        var incomeSummary = incomeRepository.findSummaryByYearAndMonth(year, month);
        var expenseSummary = expenseRepository.readExpenseSummaryByYearAndMonth(year, month);
        var categorySummary = expenseRepository.readExpenseSummaryByDescriptionAndYearAndMonth(year, month);
        return ResponseEntity.ok(new DetailedSummaryData(incomeSummary, expenseSummary, categorySummary));
    }


}
