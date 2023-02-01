package budget.control.api.domain.service;

import budget.control.api.domain.model.dto.DetailedExpenseData;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Service
public class ExpenseVerifyDescriptionService {

    @Autowired
    private ExpenseService expenseService;

    public ResponseEntity<Page<DetailedExpenseData>> hasDescriptionOrCategory(String description, String category, Pageable pageable) {
        if(description != null || category != null) {
            return expenseService.readAllExpenseByDescriptionOrCategory(description, category, pageable);
        }
        return expenseService.readAllExpense(pageable);
    }

}
