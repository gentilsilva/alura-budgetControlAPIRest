package budget.control.api.domain.controller;

import budget.control.api.domain.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("summary")
public class SummaryController {

    @Autowired
    SummaryService summaryService;

    @GetMapping("{year}/{month}")
    public ResponseEntity<?> readMonthSummary(@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month) {
        return summaryService.readIncomeSummaryByYearAndMonth(year, month);
    }
}
