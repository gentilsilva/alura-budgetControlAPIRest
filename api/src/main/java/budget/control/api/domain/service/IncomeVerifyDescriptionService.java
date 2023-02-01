package budget.control.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import budget.control.api.domain.model.dto.DetailedIncomeData;

@Service
public class IncomeVerifyDescriptionService {

    @Autowired
    private IncomeService incomeService;

    public ResponseEntity<Page<DetailedIncomeData>> hasDescription(String description, Pageable pageable) {
        if(description != null) {
            return incomeService.readAllIncomeByDescription(description, pageable);
        }
        return incomeService.readAllIncome(pageable);
    }
}
