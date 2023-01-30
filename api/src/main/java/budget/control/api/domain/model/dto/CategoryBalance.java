package budget.control.api.domain.model.dto;

import org.springframework.beans.factory.annotation.*;

import java.math.*;

public interface CategoryBalance {

    @Value("#{target.category}")
    String getCategory();
    BigDecimal getTotalValue();

}
