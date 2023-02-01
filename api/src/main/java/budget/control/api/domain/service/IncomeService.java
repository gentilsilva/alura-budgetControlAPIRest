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

    public ResponseEntity<DetailedIncomeData> createIncome(IncomeRegistration incomeRegistration, UriComponentsBuilder uriBuilder) {
        if(Boolean.TRUE.equals(incomeRegistration.isRepeatable(incomeRepository))) {
            return ResponseEntity.badRequest().build();
        }

        var income = new Income(incomeRegistration);
        incomeRepository.save(income);

        var uri = uriBuilder.path("/incomes/{id}").buildAndExpand(income.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailedIncomeData(income));
    }

    public ResponseEntity<Page<DetailedIncomeData>> readAllIncome(Pageable pageable) {
        var incomeList = incomeRepository.findAllByActiveTrue(pageable).map(DetailedIncomeData::new);

        return ResponseEntity.ok(incomeList);
    }

    public ResponseEntity<DetailedIncomeData> readIncomeById(Long id) {
        var income = incomeRepository.getReferenceByIdAndActiveTrue(id);

        return ResponseEntity.ok(new DetailedIncomeData(income));
    }

    public ResponseEntity<Page<DetailedIncomeData>> readAllIncomeByDescription(String description, Pageable pageable) {
        return ResponseEntity.ok(incomeRepository.findAllByActiveTrueAndDescription(description.toUpperCase(), pageable).map(DetailedIncomeData::new));
    }

    public ResponseEntity<Page<DetailedIncomeData>> readAllIncomeByYearAndMonth(Integer year, Integer month, Pageable pageable) {
        return ResponseEntity.ok(incomeRepository.findAllIncomeByActiveTrueAndYearAndMonth(year, month, pageable).map(DetailedIncomeData::new));
    }

    public ResponseEntity<DetailedIncomeData> updateIncomeById(Long id, IncomeUpdateData incomeUpdateData) {
        if(Boolean.TRUE.equals(incomeUpdateData.isRepeatable(incomeRepository))) {
            return ResponseEntity.badRequest().build();
        }
       var income = incomeRepository.getReferenceByIdAndActiveTrue(id);
       income.updateIncome(incomeUpdateData);

       return ResponseEntity.ok(new DetailedIncomeData(income));
    }

    public ResponseEntity<DetailedIncomeData> deleteIncomeById(Long id) {
        var income = incomeRepository.getReferenceByIdAndActiveTrue(id);
        income.inactivateIncome();

        return ResponseEntity.noContent().build();
    }

}
