package budget.control.api.domain.service;

import budget.control.api.domain.model.Expense;
import budget.control.api.domain.model.dto.DetailedExpenseData;
import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
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

    public ResponseEntity createExpense(@Valid ExpenseRegistration expenseRegistration, UriComponentsBuilder uriBuilder) {
        if(expenseRegistration.isRepeatable(expenseRepository)) {
            return ResponseEntity.badRequest().body("Despesa já registrada no mês");
        }
        var expense = new Expense(expenseRegistration);
        expenseRepository.save(expense);

        var uri = uriBuilder.path("/expenses/{id}").buildAndExpand(expense.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailedExpenseData(expense));
    }

    public ResponseEntity readAllExpense() {
        var expenseList = expenseRepository.findAllByActiveTrue();

        return ResponseEntity.ok().body(expenseList.stream().map(DetailedExpenseData::new));
    }

    public ResponseEntity readExpenseById(Long id) {
        var expense = expenseRepository.getReferenceByIdAndActiveTrue(id);

        return ResponseEntity.ok(new DetailedExpenseData(expense));
    }

    public ResponseEntity updateExpenseById(Long id, ExpenseUpdateData expenseUpdateData) {
        if(expenseUpdateData.isRepeatable(expenseRepository)) {
            return ResponseEntity.badRequest().body("Despesa já registrada no mês");
        }
        var income = expenseRepository.getReferenceByIdAndActiveTrue(id);
        income.updateExpense(expenseUpdateData);

        return ResponseEntity.ok(new DetailedExpenseData(income));
    }

    public ResponseEntity deleteExpenseById(Long id) {
        var expense = expenseRepository.getReferenceByIdAndActiveTrue(id);
        expense.inactivateExpense();

        return ResponseEntity.noContent().build();
    }
}
