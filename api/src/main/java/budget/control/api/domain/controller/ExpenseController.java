package budget.control.api.domain.controller;

import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.service.ExpenseService;
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
@RequestMapping("expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid ExpenseRegistration expenseRegistration, UriComponentsBuilder uriBuilder) {
        return expenseService.create(expenseRegistration, uriBuilder);
    }

}
