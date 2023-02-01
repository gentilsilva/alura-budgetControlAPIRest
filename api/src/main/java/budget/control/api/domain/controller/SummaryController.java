package budget.control.api.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import budget.control.api.domain.model.dto.DetailedSummaryData;
import budget.control.api.domain.service.SummaryService;

@RestController
@RequestMapping("summary")
public class SummaryController {

    @Autowired
    SummaryService summaryService;

    @GetMapping("{year}/{month}")
    public ResponseEntity<DetailedSummaryData> readMonthSummary(@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month) {
        return summaryService.readIncomeSummaryByYearAndMonth(year, month);
    }
}
