package budget.control.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import budget.control.api.domain.model.Expense;
import budget.control.api.domain.model.converter.CategoryConverter;
import budget.control.api.domain.model.dto.DetailedExpenseData;
import budget.control.api.domain.model.form.ExpenseRegistration;
import budget.control.api.domain.model.form.ExpenseUpdateData;
import budget.control.api.domain.repository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	public ResponseEntity<DetailedExpenseData> createExpense(ExpenseRegistration expenseRegistration,
			CategoryConverter categoryConverter, UriComponentsBuilder uriBuilder) {
		if (Boolean.TRUE.equals(expenseRegistration.isRepeatable(expenseRepository))) {
			return ResponseEntity.badRequest().build();
		}
		var expense = new Expense(expenseRegistration, categoryConverter);
		expenseRepository.save(expense);

		var uri = uriBuilder.path("/expenses/{id}").buildAndExpand(expense.getId()).toUri();

		return ResponseEntity.created(uri).body(new DetailedExpenseData(expense));
	}

	public ResponseEntity<Page<DetailedExpenseData>> readAllExpense(Pageable pageable) {
		return ResponseEntity.ok(expenseRepository.findAllByActiveTrue(pageable).map(DetailedExpenseData::new));
	}

	public ResponseEntity<Page<DetailedExpenseData>> readAllExpenseByDescriptionOrCategory(String description,
			String category, Pageable pageable) {
		CategoryConverter categoryConverter = new CategoryConverter();
		return ResponseEntity.ok(expenseRepository
				.findAllByOptionalFilters(description, categoryConverter.convertToEntityAttribute(category), pageable)
				.map(DetailedExpenseData::new));
	}

	public ResponseEntity<DetailedExpenseData> readExpenseById(Long id) {
		return ResponseEntity.ok(new DetailedExpenseData(expenseRepository.getReferenceByIdAndActiveTrue(id)));
	}

	public ResponseEntity<DetailedExpenseData> updateExpenseById(Long id, ExpenseUpdateData expenseUpdateData,
			CategoryConverter categoryConverter) {
		if (Boolean.TRUE.equals(expenseUpdateData.isRepeatable(expenseRepository))) {
			return ResponseEntity.badRequest().build();
		}
		var income = expenseRepository.getReferenceByIdAndActiveTrue(id);
		income.updateExpense(expenseUpdateData, categoryConverter);

		return ResponseEntity.ok(new DetailedExpenseData(income));
	}

	public ResponseEntity<DetailedExpenseData> deleteExpenseById(Long id) {
		var expense = expenseRepository.getReferenceByIdAndActiveTrue(id);
		expense.inactivateExpense();

		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Page<DetailedExpenseData>> readAllExpenseByYearAndMonth(Integer year, Integer month,
			Pageable pageable) {
		return ResponseEntity.ok(expenseRepository.findAllExpenseByActiveTrueAndYearAndMonth(year, month, pageable)
				.map(DetailedExpenseData::new));
	}
}
