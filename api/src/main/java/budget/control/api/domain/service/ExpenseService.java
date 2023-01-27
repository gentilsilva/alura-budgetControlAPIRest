package budget.control.api.domain.service;

import budget.control.api.domain.model.Category;
import budget.control.api.domain.model.Expense;
import budget.control.api.domain.model.dto.DetailedExpenseData;
import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
import budget.control.api.domain.repository.ExpenseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ExpenseRegistrationService expenseRegistrationService;

    @Autowired
    ExpenseUpdateDataService expenseUpdateDataService;

    public ResponseEntity<?> createExpense(@Valid ExpenseRegistration expenseRegistration, UriComponentsBuilder uriBuilder) {
        if(expenseRegistration.isRepeatable(expenseRepository)) {
            return ResponseEntity.badRequest().body("Despesa já registrada no mês");
        }
        var expense = new Expense(expenseRegistration, expenseRegistrationService);
        expenseRepository.save(expense);

        var uri = uriBuilder.path("/expenses/{id}").buildAndExpand(expense.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailedExpenseData(expense));
    }

    public ResponseEntity<Page<?>> readAllExpense(Pageable pageable) {
        return ResponseEntity.ok(expenseRepository.findAllByActiveTrue(pageable).map(DetailedExpenseData::new));
    }

    public ResponseEntity<Page<?>> readAllExpenseByDescriptionOrCategory(String description, String category, Pageable pageable) {
        return ResponseEntity.ok(expenseRepository.findAllByActiveTrueAndDescriptionOrCategory(description, Category.valueOf(category.toUpperCase()),  pageable).map(DetailedExpenseData::new));
    }

    public ResponseEntity<?> readExpenseById(Long id) {
        return ResponseEntity.ok(new DetailedExpenseData(expenseRepository.getReferenceByIdAndActiveTrue(id)));
    }

    public ResponseEntity<?> updateExpenseById(Long id, ExpenseUpdateData expenseUpdateData) {
        if(expenseUpdateData.isRepeatable(expenseRepository)) {
            return ResponseEntity.badRequest().body("Despesa já registrada no mês");
        }
        var income = expenseRepository.getReferenceByIdAndActiveTrue(id);
        income.updateExpense(expenseUpdateData, expenseUpdateDataService);

        return ResponseEntity.ok(new DetailedExpenseData(income));
    }

    public ResponseEntity<?> deleteExpenseById(Long id) {
        var expense = expenseRepository.getReferenceByIdAndActiveTrue(id);
        expense.inactivateExpense();

        return ResponseEntity.noContent().build();
    }

}
