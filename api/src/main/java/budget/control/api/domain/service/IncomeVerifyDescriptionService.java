package budget.control.api.domain.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Service
public class IncomeVerifyDescriptionService {

    @Autowired
    private IncomeService incomeService;

    public ResponseEntity<Page<?>> hasDescription(String description, Pageable pageable) {
        if(description != null) {
            return incomeService.readAllIncomeByDescription(description, pageable);
        }
        return incomeService.readAllIncome(pageable);
    }
}
