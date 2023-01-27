package budget.control.api.domain.controller;

import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
import budget.control.api.domain.service.ExpenseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    @Transactional
    public ResponseEntity createExpense(@RequestBody @Valid ExpenseRegistration expenseRegistration, UriComponentsBuilder uriBuilder) {
        return expenseService.createExpense(expenseRegistration, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<?>> readAllExpense(String category, Pageable pageable) {
        if(category == null) {
            return expenseService.readAllExpense(pageable);
        }
        return expenseService.readAllExpenseByCategory(category, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity readExpenseById(@PathVariable Long id) {
        return expenseService.readExpenseById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateExpenseById(@PathVariable Long id, @RequestBody @Valid ExpenseUpdateData expenseUpdateData) {
        return expenseService.updateExpenseById(id, expenseUpdateData);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleteExpenseById(@PathVariable Long id) {
        return expenseService.deleteExpenseById(id);
    }

}
