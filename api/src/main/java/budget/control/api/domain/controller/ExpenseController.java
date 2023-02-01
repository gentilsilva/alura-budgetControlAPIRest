package budget.control.api.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import budget.control.api.domain.model.converter.CategoryConverter;
import budget.control.api.domain.model.dto.DetailedExpenseData;
import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
import budget.control.api.domain.service.ExpenseService;
import budget.control.api.domain.service.ExpenseVerifyDescriptionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseVerifyDescriptionService expenseVerifyDescriptionService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailedExpenseData> createExpense(@RequestBody @Valid ExpenseRegistration expenseRegistration, CategoryConverter categoryConverter, UriComponentsBuilder uriBuilder) {
        return expenseService.createExpense(expenseRegistration, categoryConverter, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DetailedExpenseData>> readAllExpense(@RequestParam(required = false) String description, @RequestParam(required = false) String category, Pageable pageable) {
        return expenseVerifyDescriptionService.hasDescriptionOrCategory(description, category, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedExpenseData> readExpenseById(@PathVariable Long id) {
        return expenseService.readExpenseById(id);
    }

    @GetMapping("{year}/{month}")
    public ResponseEntity<Page<DetailedExpenseData>> readAllExpenseByYearAndMonth(@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month, Pageable pageable) {
        return expenseService.readAllExpenseByYearAndMonth(year, month, pageable);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailedExpenseData> updateExpenseById(@PathVariable Long id, @RequestBody @Valid ExpenseUpdateData expenseUpdateData, CategoryConverter categoryConverter) {
        return expenseService.updateExpenseById(id, expenseUpdateData, categoryConverter);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<DetailedExpenseData> deleteExpenseById(@PathVariable Long id) {
        return expenseService.deleteExpenseById(id);
    }

}
