package budget.control.api.domain.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Service
public class ExpenseVerifyDescriptionService {

    @Autowired
    private ExpenseService expenseService;

    public ResponseEntity<Page<?>> hasDescription(String description, Pageable pageable) {
        if(description != null) {
            return expenseService.readAllExpenseByDescription(description, pageable);
        }
        return expenseService.readAllExpense(pageable);
    }

}
