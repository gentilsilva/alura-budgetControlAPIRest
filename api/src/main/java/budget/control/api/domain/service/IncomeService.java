package budget.control.api.domain.service;

import budget.control.api.domain.model.Income;
import budget.control.api.domain.model.dto.DetailedIncomeData;
import budget.control.api.domain.model.form.IncomeRegistration;
import budget.control.api.domain.model.form.IncomeUpdateData;
import budget.control.api.domain.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRegistrationService expenseRegistrationService;

    @Autowired
    private ExpenseUpdateDataService expenseUpdateDataService;

    public ResponseEntity createIncome( IncomeRegistration incomeRegistration, UriComponentsBuilder uriBuilder) {
        if(incomeRegistration.isRepeatable(incomeRepository)) {
            return ResponseEntity.badRequest().body("Receita já registrada no mês");
        }

        var income = new Income(incomeRegistration);
        incomeRepository.save(income);

        var uri = uriBuilder.path("/incomes/{id}").buildAndExpand(income.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailedIncomeData(income));
    }

    public ResponseEntity<Page<?>> readAllIncome(Pageable pageable) {
        var incomeList = incomeRepository.findAllByActiveTrue(pageable).map(DetailedIncomeData::new);

        return ResponseEntity.ok(incomeList);
    }

    public ResponseEntity readIncomeById(Long id) {
        var income = incomeRepository.getReferenceByIdAndActiveTrue(id);

        return ResponseEntity.ok(new DetailedIncomeData(income));
    }

    public ResponseEntity<Page<?>> readIncomeByDescription(Pageable pageable, String description) {
        return ResponseEntity.ok(incomeRepository.findAllByActiveTrueAndDescription(pageable, description.toUpperCase()).map(DetailedIncomeData::new));
    }

    public ResponseEntity updateIncomeById(Long id, IncomeUpdateData incomeUpdateData) {
        if(incomeUpdateData.isRepeatable(incomeRepository)) {
            return ResponseEntity.badRequest().body("Receita já registrada no mês");
        }
       var income = incomeRepository.getReferenceByIdAndActiveTrue(id);
       income.updateIncome(incomeUpdateData);

       return ResponseEntity.ok(new DetailedIncomeData(income));
    }

    public ResponseEntity deleteIncomeById(Long id) {
        var income = incomeRepository.getReferenceByIdAndActiveTrue(id);
        income.inactivateIncome();

        return ResponseEntity.noContent().build();
    }

}
