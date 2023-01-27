package budget.control.api.domain.service;

import budget.control.api.domain.model.Category;
import budget.control.api.domain.model.form.ExpenseUpdateData;
import org.springframework.stereotype.Service;

@Service
public class ExpenseUpdateDataService {

    public Category isNull(ExpenseUpdateData expenseUpdateData) {
        if(expenseUpdateData.category().isBlank()) {
            return Category.OUTRAS;
        } else {
            return Category.valueOf(expenseUpdateData.category().toUpperCase());
        }
    }

}
