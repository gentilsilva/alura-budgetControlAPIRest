package budget.control.api.domain.model.converter;

import java.util.stream.Stream;

import budget.control.api.domain.model.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
	public String convertToDatabaseColumn(Category category) {
		if(category == null) {
			return null;
		}
		return category.getCode().toUpperCase();
	}

	@Override
	public Category convertToEntityAttribute(String code) {
		if(code == null) {
			return null;
		}
		return Stream.of(Category.values())
				.filter(c -> c.getCode().equals(code))
				.findFirst()
				.orElse(Category.OUTRAS);
	}

}
