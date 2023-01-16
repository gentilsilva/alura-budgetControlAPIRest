package budget.control.api.domain.service;

import budget.control.api.domain.model.Expense;
import budget.control.api.domain.model.dto.DetailedExpenseData;
import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.repository.ExpenseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public ResponseEntity create(@Valid ExpenseRegistration expenseRegistration, UriComponentsBuilder uriBuilder) {
        if(expenseRegistration.isRepeatable(expenseRepository)) {
            return ResponseEntity.badRequest().body("Despesa já registrada no mês");
        }
        var expense = new Expense(expenseRegistration);
        expenseRepository.save(expense);

        var uri = uriBuilder.path("/expenses/{id}").buildAndExpand(expense.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailedExpenseData(expense));
    }

}
