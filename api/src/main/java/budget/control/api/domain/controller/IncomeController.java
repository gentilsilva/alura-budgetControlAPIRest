package budget.control.api.domain.controller;

import budget.control.api.domain.model.form.IncomeRegistration;
import budget.control.api.domain.model.form.IncomeUpdateData;
import budget.control.api.domain.service.IncomeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    @Transactional
    public ResponseEntity createIncome(@RequestBody @Valid IncomeRegistration incomeRegistration, UriComponentsBuilder uriBuilder) {
        return incomeService.createIncome(incomeRegistration, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<?>> readAllIncome(@PageableDefault(size = 10) Pageable pageable) {
        return incomeService.readAllIncome(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity readIncomeById(@PathVariable Long id) {
        return incomeService.readIncomeById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateIncomeById(@PathVariable Long id, @RequestBody @Valid IncomeUpdateData incomeUpdateData) {
        return incomeService.updateIncomeById(id, incomeUpdateData);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleteIncomeById(@PathVariable Long id) {
        return incomeService.deleteIncomeById(id);
    }

}
