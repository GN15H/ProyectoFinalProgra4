package controlador;

import java.time.LocalDate;
import java.util.List;


public abstract class Validator<T, E extends Enum<E>> {
	
	public abstract E validate(List<String> elements);
	
	public abstract T parseObject(List<Object> elements);	
	
	protected boolean areFieldsEmpty(String... fields) {
		for(String item: fields) {
			if(item.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isNumeric(String str) {
		return str.matches("\\d+");
	}
	
	protected boolean isDecimal(String str) {
		return str.matches("^\\d+(\\.\\d+)?$");
	}
	
	protected boolean isDate(String str) {
		return str.matches("^\\d\\d\\d\\d\\/((0[13578]|1[02])\\/([0-2][0-9]|3[01])|(0[469]|11)\\/([0-2][0-9]|30)|02\\/([0-1][0-9]|2[0-8]))$");
	}
	
	protected boolean invalidDates(LocalDate arrivalDate, LocalDate departureDate) {
		return departureDate.isBefore(arrivalDate) || arrivalDate.isBefore(LocalDate.now());
	}
}
