package budget.control.api.domain.service;

import budget.control.api.domain.model.Category;
import budget.control.api.domain.model.form.ExpenseRegistration;
import org.springframework.stereotype.Service;

@Service
public class ExpenseRegistrationService {

    public Category isNull(ExpenseRegistration expenseRegistration) {
        if(expenseRegistration.category().isBlank()) {
            return Category.OUTRAS;
        } else {
            return Category.valueOf(expenseRegistration.category().toUpperCase());
        }
    }

}
