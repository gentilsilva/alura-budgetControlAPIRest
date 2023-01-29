package budget.control.api.domain.controller;

import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
import budget.control.api.domain.service.*;
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

    @Autowired
    private ExpenseVerifyDescriptionService expenseVerifyDescriptionService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createExpense(@RequestBody @Valid ExpenseRegistration expenseRegistration, UriComponentsBuilder uriBuilder) {
        return expenseService.createExpense(expenseRegistration, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<?>> readAllExpense(@RequestParam(required = false) String description, Pageable pageable) {
        return expenseVerifyDescriptionService.hasDescription(description, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readExpenseById(@PathVariable Long id) {
        return expenseService.readExpenseById(id);
    }

    @GetMapping("{year}/{month}")
    public ResponseEntity<Page<?>> readAllExpenseByYearAndMonth(@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month, Pageable pageable) {
        return expenseService.readAllExpenseByYearAndMonth(year, month, pageable);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateExpenseById(@PathVariable Long id, @RequestBody @Valid ExpenseUpdateData expenseUpdateData) {
        return expenseService.updateExpenseById(id, expenseUpdateData);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deleteExpenseById(@PathVariable Long id) {
        return expenseService.deleteExpenseById(id);
    }

}
