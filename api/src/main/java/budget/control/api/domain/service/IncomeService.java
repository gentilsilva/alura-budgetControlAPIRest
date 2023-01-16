package budget.control.api.domain.service;

import budget.control.api.domain.model.Income;
import budget.control.api.domain.model.dto.DetailedIncomeData;
import budget.control.api.domain.model.form.IncomeRegistration;
import budget.control.api.domain.repository.IncomeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ResponseEntity create(@Valid IncomeRegistration incomeRegistration, UriComponentsBuilder uriBuilder) {
        if(incomeRegistration.isRepeatable(incomeRepository)) {
            return ResponseEntity.badRequest().body("Receita já registrada no mês");
        }

        var income = new Income(incomeRegistration);
        incomeRepository.save(income);

        var uri = uriBuilder.path("/incomes/{id}").buildAndExpand(income.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailedIncomeData(income));
    }

}
