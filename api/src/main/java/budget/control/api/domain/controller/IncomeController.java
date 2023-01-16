package budget.control.api.domain.controller;

import budget.control.api.domain.model.form.IncomeRegistration;
import budget.control.api.domain.service.IncomeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid IncomeRegistration incomeRegistration, UriComponentsBuilder uriBuilder) {
        return incomeService.create(incomeRegistration, uriBuilder);
    }

}
