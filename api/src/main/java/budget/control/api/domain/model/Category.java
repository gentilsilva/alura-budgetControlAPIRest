package budget.control.api.domain.model;

import lombok.Getter;

@Getter
public enum Category {

    ALIMENTACAO("alimentacao"),
    SAUDE("saude"),
    MORADIA("moradia"),
    TRANSPORTE("transporte"),
    EDUCACAO("educacao"),
    LAZER("lazer"),
    IMPREVISTOS("imprevistos"),
    OUTRAS("outras");
	
	private String code;

	Category(String code) {
		this.code = code;
	}

}
