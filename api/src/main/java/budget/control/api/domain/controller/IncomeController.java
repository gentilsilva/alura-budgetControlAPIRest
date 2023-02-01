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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import budget.control.api.domain.model.dto.DetailedIncomeData;
import budget.control.api.domain.model.form.IncomeRegistration;
import budget.control.api.domain.model.form.IncomeUpdateData;
import budget.control.api.domain.service.IncomeService;
import budget.control.api.domain.service.IncomeVerifyDescriptionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    IncomeVerifyDescriptionService incomeVerifyDescriptionService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailedIncomeData> createIncome(@RequestBody @Valid IncomeRegistration incomeRegistration, UriComponentsBuilder uriBuilder) {
        return incomeService.createIncome(incomeRegistration, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DetailedIncomeData>> readAllIncome(String description, Pageable pageable) {
        return incomeVerifyDescriptionService.hasDescription(description, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedIncomeData> readIncomeById(@PathVariable Long id) {
        return incomeService.readIncomeById(id);
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<Page<DetailedIncomeData>> readAllIncomeByYearAndMonth(@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month, Pageable pageable) {
        return incomeService.readAllIncomeByYearAndMonth(year, month, pageable);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailedIncomeData> updateIncomeById(@PathVariable Long id, @RequestBody @Valid IncomeUpdateData incomeUpdateData) {
        return incomeService.updateIncomeById(id, incomeUpdateData);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<DetailedIncomeData> deleteIncomeById(@PathVariable Long id) {
        return incomeService.deleteIncomeById(id);
    }

}
